package ru.knowledge.cocktailapp.cocktails.cocktails

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

class CocktailsViewModel(private val repository: CocktailRepository) : ViewModel() {

    val cocktailList: LiveData<List<CocktailDto>> get() = mutableCocktailList
    private val mutableCocktailList = MutableLiveData<List<CocktailDto>>()

    val errorState: LiveData<String> get() = mutableErrorState
    private val mutableErrorState = MutableLiveData<String>()

    fun resetError() {
        mutableErrorState.postValue("")
    }

    fun getRefreshCocktails() {
        val handler = CoroutineExceptionHandler { context, exception ->
            Log.e("Cocktails", "CocktailsViewModel.getRefreshCocktails() exception $exception")
            mutableErrorState.postValue("Network error")
        }
        viewModelScope.launch(Dispatchers.IO + handler) {
            repository.getRefreshCocktails().collect { result ->
                result.fold(onSuccess = {
                    mutableCocktailList.postValue(it)
                }, onFailure = {
                    mutableErrorState.postValue("Network error")
                })
            }
        }
    }

    fun getCocktails() {
        val handler = CoroutineExceptionHandler { context, exception ->
            Log.e("Cocktails", "CocktailsViewModel.getCocktails() exception $exception")
            mutableErrorState.postValue("Network error")
        }
        viewModelScope.launch(Dispatchers.IO + handler) {
            repository.getCocktails().collect { result ->
                result.fold(onSuccess = {
                    mutableCocktailList.postValue(it)
                }, onFailure = {
                    mutableErrorState.postValue("Network error")
                })
            }
        }
    }
}