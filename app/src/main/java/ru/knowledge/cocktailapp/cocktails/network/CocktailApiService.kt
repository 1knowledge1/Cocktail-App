package ru.knowledge.cocktailapp.cocktails.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.knowledge.cocktailapp.cocktails.network.dto.CocktailsByCategoryResponse
import ru.knowledge.cocktailapp.cocktails.network.dto.CocktailsByIdResponse

const val BASE_URL = "https://thecocktaildb.com/api/json/v1/1/"

interface CocktailApiService {
    @GET("filter.php")
    suspend fun getCocktailsIdByCategory(
        @Query("c") category: String = "Ordinary_Drink"
    ): CocktailsByCategoryResponse?

    @GET("lookup.php?i=11007")
    suspend fun getCocktailById(
        @Query("i") id: Long
    ): CocktailsByIdResponse?

    companion object {
        fun create(): CocktailApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CocktailApiService::class.java)
        }
    }
}