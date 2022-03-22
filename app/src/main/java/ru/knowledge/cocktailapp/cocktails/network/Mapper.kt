package ru.knowledge.cocktailapp.cocktails.network

import ru.knowledge.cocktailapp.cocktails.database.dto.CocktailDto
import ru.knowledge.cocktailapp.cocktails.database.dto.IngredientDto
import ru.knowledge.cocktailapp.cocktails.network.dto.CocktailDetailedResponse
import ru.knowledge.cocktailapp.cocktails.network.dto.DrinksResponse

fun DrinksResponse.toCocktailDto() = CocktailDto(
    id ?: 0,
    name ?: "Unknown",
    imageUrl ?: "",
    "",
    "",
    "",
    null)

fun CocktailDetailedResponse.toCocktailDto(): CocktailDto {
    fun getImageUrl(name: String): String {
        return "https://thecocktaildb.com/images/ingredients/${name.lowercase()}-Medium.png"
    }

    val ingredients = mutableListOf<IngredientDto>()
    ingredient1?.let { ingredients.add(IngredientDto(it, getImageUrl(it), measure1 ?: "")) }
    ingredient2?.let { ingredients.add(IngredientDto(it, getImageUrl(it), measure2 ?: "")) }
    ingredient3?.let { ingredients.add(IngredientDto(it, getImageUrl(it), measure3 ?: "")) }
    ingredient4?.let { ingredients.add(IngredientDto(it, getImageUrl(it), measure4 ?: "")) }
    ingredient5?.let { ingredients.add(IngredientDto(it, getImageUrl(it), measure5 ?: "")) }
    ingredient6?.let { ingredients.add(IngredientDto(it, getImageUrl(it), measure6 ?: "")) }
    ingredient7?.let { ingredients.add(IngredientDto(it, getImageUrl(it), measure7 ?: "")) }
    ingredient8?.let { ingredients.add(IngredientDto(it, getImageUrl(it), measure8 ?: "")) }
    ingredient9?.let { ingredients.add(IngredientDto(it, getImageUrl(it), measure9 ?: "")) }
    ingredient10?.let { ingredients.add(IngredientDto(it, getImageUrl(it), measure10 ?: "")) }
    ingredient11?.let { ingredients.add(IngredientDto(it, getImageUrl(it), measure11 ?: "")) }
    ingredient12?.let { ingredients.add(IngredientDto(it, getImageUrl(it), measure12 ?: "")) }
    ingredient13?.let { ingredients.add(IngredientDto(it, getImageUrl(it), measure13 ?: "")) }
    ingredient14?.let { ingredients.add(IngredientDto(it, getImageUrl(it), measure14 ?: "")) }
    ingredient15?.let { ingredients.add(IngredientDto(it, getImageUrl(it), measure15 ?: "")) }

    return CocktailDto(id ?: 0, name ?: "Unknown", imageUrl ?: "",
        category ?: "", alcoholType ?: "", instruction ?: "", ingredients)
}