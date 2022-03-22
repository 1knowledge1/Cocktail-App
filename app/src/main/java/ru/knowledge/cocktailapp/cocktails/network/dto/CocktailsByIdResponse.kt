package ru.knowledge.cocktailapp.cocktails.network.dto

import com.google.gson.annotations.SerializedName

data class CocktailsByIdResponse(
    @SerializedName("drinks")
    val drinks: List<CocktailDetailedResponse>?
)
