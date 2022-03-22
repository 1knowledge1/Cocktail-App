package ru.knowledge.cocktailapp.cocktails.detailed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.knowledge.cocktailapp.cocktails.CocktailRepository
import ru.knowledge.cocktailapp.cocktails.database.dto.CocktailDto

class CocktailDetailedViewModel(private val repository: CocktailRepository) : ViewModel() {

    val cocktail: LiveData<CocktailDto> get() = mutableCocktail
    private val mutableCocktail = MutableLiveData<CocktailDto>()

    val errorState: LiveData<String> get() = mutableErrorState
    private val mutableErrorState = MutableLiveData<String>()

    fun resetError() {
        mutableErrorState.postValue("")
    }

    fun getCocktailById(cocktailId: Long) {
        val handler = CoroutineExceptionHandler { context, exception ->
            Log.e("Cocktails",
                "CocktailDetailedViewModel.getCocktailById() exception $exception")
            mutableErrorState.postValue("Network error")
        }
        viewModelScope.launch(Dispatchers.IO + handler) {
            repository.getCocktailById(cocktailId).collect { result ->
                result.fold(onSuccess = {
                    mutableCocktail.postValue(it)
                }, onFailure = {
                    mutableErrorState.postValue("Network error")
                })
            }
        }
    }
}