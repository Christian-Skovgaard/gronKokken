package com.example.gronkokken.ui.pages.frontpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gronkokken.repository.Firestore
import kotlinx.coroutines.launch

class FrontPageViewModel: ViewModel() { //Christian
    val firestore = Firestore()

    var currentRecipeId = ""
    init {
        viewModelScope.launch {
            currentRecipeId = firestore.getCurrentRecipeId()
        }
    }
}