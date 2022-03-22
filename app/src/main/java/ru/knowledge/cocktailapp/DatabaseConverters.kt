package ru.knowledge.cocktailapp

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.knowledge.cocktailapp.cocktails.database.dto.IngredientDto

class DatabaseConverters {
    @TypeConverter
    fun ingredientsToString(value: List<IngredientDto>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun stringToIngredients(value: String?): List<IngredientDto>? {
        val listIngredients = object : TypeToken<List<IngredientDto>>() {}.type
        return Gson().fromJson(value, listIngredients)
    }
}