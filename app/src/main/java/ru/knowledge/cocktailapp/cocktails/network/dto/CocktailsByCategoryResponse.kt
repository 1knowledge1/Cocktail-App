package ru.knowledge.cocktailapp.cocktails.network.dto

import com.google.gson.annotations.SerializedName

data class CocktailsByCategoryResponse(
    @SerializedName("drinks")
    val drinks: List<DrinksResponse>?
)
