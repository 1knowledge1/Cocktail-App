package ru.knowledge.cocktailapp.cocktails.network.dto

import com.google.gson.annotations.SerializedName

data class DrinksResponse(
    @SerializedName("idDrink")
    val id: Long?,
    @SerializedName("strDrink")
    val name: String?,
    @SerializedName("strDrinkThumb")
    val imageUrl: String?
)