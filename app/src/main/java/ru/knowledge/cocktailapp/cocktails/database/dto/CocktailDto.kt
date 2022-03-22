package ru.knowledge.cocktailapp.cocktails.database.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktails")
data class CocktailDto(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "alcohol_type")
    val alcoholType: String,
    @ColumnInfo(name = "instruction")
    val instruction: String,
    @ColumnInfo(name = "ingredients")
    val ingredients: List<IngredientDto>?
)