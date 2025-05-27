package com.example.gronkokken.ui.pages.seasonalIngredientsScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gronkokken.repository.Firestore
import com.example.gronkokken.models.SeasonalIngredient
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Month
import java.util.Locale

//Lukas

class SeasonalIngredientsViewmodel(): ViewModel() {

    private val fireStore = Firestore()

    var loading: MutableState<Boolean> = mutableStateOf(true)

    private val _ingredientsList = mutableStateOf<List<SeasonalIngredient>>(emptyList())
    val ingredientsList: State<List<SeasonalIngredient>> = _ingredientsList

    private val _inSeasonList = mutableStateOf<List<SeasonalIngredient>>(emptyList())
    val inSeasonList: State<List<SeasonalIngredient>> = _inSeasonList

    init {
        viewModelScope.launch {
            val importList = fireStore.getSeasonalIngredients()
            _ingredientsList.value = importList

            val currentMonth  = LocalDate.now().monthValue
            _inSeasonList.value = importList.filter { it.isInSeason(currentMonth) }

            loading.value = false

        }
    }

    fun getCurrentSeason(): String {
        val currentMonth = LocalDate.now().monthValue

        return when (currentMonth) {
            in 3..5 -> "Forår"
            in 6..8 -> "Sommer"
            in 9..11 -> "Efterår"
            else -> "Vinter"
        }
    }

    fun getCurrentMonth(): String {
        val currentMonth = LocalDate.now().monthValue

        val currentMonthName = Month.entries[currentMonth - 1]

        return currentMonthName.toString().lowercase().replaceFirstChar { it.uppercase() }
    }


}
