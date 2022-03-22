package ru.knowledge.cocktailapp.cocktails.database

import androidx.room.*
import ru.knowledge.cocktailapp.cocktails.database.dto.CocktailDto

@Dao
interface CocktailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCocktails(cocktails: List<CocktailDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCocktail(cocktail: CocktailDto)

    @Query("SELECT * FROM cocktails")
    suspend fun getCocktails(): List<CocktailDto>?

    @Query("SELECT * FROM cocktails WHERE id == :id")
    suspend fun getCocktailById(id: Long): CocktailDto?

    @Query("DELETE FROM cocktails")
    suspend fun deleteAllCocktails()

    @Query("DELETE FROM cocktails WHERE id == :id")
    suspend fun deleteCocktailById(id: Long)

    @Update
    suspend fun updateCocktail(cocktail: CocktailDto)
}