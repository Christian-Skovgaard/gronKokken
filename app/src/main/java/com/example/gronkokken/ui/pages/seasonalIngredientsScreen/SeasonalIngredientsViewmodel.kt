package com.example.gronkokken.ui.pages.seasonalIngredientsScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gronkokken.repository.Firestore
import com.example.gronkokken.models.SeasonalIngredient
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

//Lukas

class SeasonalIngredientsViewmodel(): ViewModel() {

    val fireStore = Firestore()

    var seasonalIngredient: SeasonalIngredient = SeasonalIngredient()

    private val _ingredientsList = mutableStateOf<List<SeasonalIngredient>>(emptyList())
    val ingredientsList: State<List<SeasonalIngredient>> = _ingredientsList

    init {
        viewModelScope.launch {
            val importList = fireStore.getSeasonalIngredients()
            _ingredientsList.value = importList
            Log.d("suppe", "Fetched: $importList")
        }
    }

}