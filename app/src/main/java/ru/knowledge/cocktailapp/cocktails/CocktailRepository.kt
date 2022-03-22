package ru.knowledge.cocktailapp.cocktails

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.knowledge.cocktailapp.cocktails.database.CocktailDao
import ru.knowledge.cocktailapp.cocktails.database.dto.CocktailDto
import ru.knowledge.cocktailapp.cocktails.network.CocktailApiService
import ru.knowledge.cocktailapp.cocktails.network.toCocktailDto
import java.io.IOException

class CocktailRepository(private val cocktailDao: CocktailDao,
                         private val cocktailApiService: CocktailApiService) {

    suspend fun getCocktails(): Flow<Result<List<CocktailDto>>> {
        return flow {
            val savedCocktails = cocktailDao.getCocktails()
            if (savedCocktails != null && savedCocktails.isNotEmpty()) {
                emit(Result.success(savedCocktails))
            } else {
                val loadedCocktails = cocktailApiService.getCocktailsIdByCategory()
                if (loadedCocktails?.drinks == null) {
                    emit(Result.failure<List<CocktailDto>>(IOException()))
                } else {
                    val cocktails = loadedCocktails.drinks
                        .map { it.toCocktailDto() }
                        .sortedBy { it.id }
                    cocktailDao.deleteAllCocktails()
                    cocktailDao.insertCocktails(cocktails)
                    emit(Result.success(cocktails))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCocktailById(id: Long): Flow<Result<CocktailDto>> {
        return flow {
            val savedCocktail = cocktailDao.getCocktailById(id)
            if (savedCocktail != null && savedCocktail.category.isNotEmpty()) {
                emit(Result.success(savedCocktail))
            } else {
                val loadedCocktail = cocktailApiService.getCocktailById(id)
                if (loadedCocktail?.drinks == null || loadedCocktail.drinks.isEmpty()) {
                    emit(Result.failure<CocktailDto>(IOException()))
                } else {
                    val cocktail = loadedCocktail.drinks[0].toCocktailDto()
                    cocktailDao.updateCocktail(cocktail)
                    emit(Result.success(cocktail))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getRefreshCocktails(): Flow<Result<List<CocktailDto>>> {
        return flow {
            val loadedCocktails = cocktailApiService.getCocktailsIdByCategory()
            if (loadedCocktails?.drinks == null) {
                emit(Result.failure<List<CocktailDto>>(IOException()))
            } else {
                val cocktails = loadedCocktails.drinks
                    .map { it.toCocktailDto() }
                    .sortedBy { it.id }
                cocktailDao.deleteAllCocktails()
                cocktailDao.insertCocktails(cocktails)
                emit(Result.success(cocktails))
            }
        }.flowOn(Dispatchers.IO)
    }
}