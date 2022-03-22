package ru.knowledge.cocktailapp.cocktails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.knowledge.cocktailapp.cocktails.cocktails.CocktailsViewModel
import ru.knowledge.cocktailapp.cocktails.detailed.CocktailDetailedViewModel

class CocktailViewModelFactory(private val repository: CocktailRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CocktailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CocktailsViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(CocktailDetailedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CocktailDetailedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}