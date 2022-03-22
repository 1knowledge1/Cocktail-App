package ru.knowledge.cocktailapp

import android.app.Application
import ru.knowledge.cocktailapp.cocktails.CocktailRepository
import ru.knowledge.cocktailapp.cocktails.network.CocktailApiService

class App : Application() {
    private val cocktailApiService by lazy { CocktailApiService.create() }
    private val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { CocktailRepository(database.cocktailDao(), cocktailApiService) }
}