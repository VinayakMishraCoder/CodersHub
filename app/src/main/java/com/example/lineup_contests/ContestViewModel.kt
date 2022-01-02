package com.example.lineup_contests

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ContestViewModel : ViewModel() {
    var allcontests: MutableLiveData<contests> = MutableLiveData()


    fun getRecipes(){
        viewModelScope.launch {
            val apiResult = RetroFitHelper.apiService.getRecipe()
            allcontests.value = apiResult?.body()
        }
    }
}