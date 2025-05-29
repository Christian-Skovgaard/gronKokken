package com.example.gronkokken.ui.pages.seasonalIngredientsScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import java.time.format.TextStyle
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gronkokken.repository.Firestore
import com.example.gronkokken.models.SeasonalIngredient
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Month
import java.util.Locale



//Lukas med lidt hjælp fra chatgpt

class SeasonalIngredientsViewmodel(): ViewModel() {

    private val fireStore = Firestore()

    var loading: MutableState<Boolean> = mutableStateOf(true)

    private val _ingredientsList = mutableStateOf<List<SeasonalIngredient>>(emptyList())
    val ingredientsList: State<List<SeasonalIngredient>> = _ingredientsList

    private val _inSeasonList = mutableStateOf<List<SeasonalIngredient>>(emptyList())
    val inSeasonList: State<List<SeasonalIngredient>> = _inSeasonList

    var selectedMonth = mutableStateOf(LocalDate.now().monthValue)
        private set

    init {
        viewModelScope.launch {
            val importList = fireStore.getSeasonalIngredients()
            val currentMonth  = LocalDate.now().monthValue

            _ingredientsList.value = importList
            _inSeasonList.value = importList.filter { it.isInSeason(currentMonth) }

            loading.value = false

        }
    }

    fun getCurrentMonth(): String {
        val currentMonth = LocalDate.now().monthValue

        val currentMonthName = Month.entries[currentMonth - 1]

        return currentMonthName.toString().lowercase().replaceFirstChar { it.uppercase() }
    }

    fun setSelectedMonth(month: Int) {
        selectedMonth.value = month
        _inSeasonList.value = _ingredientsList.value.filter {
            it.isInSeason(month)
        }
    }

    fun getSelectedMonthName(): String {
        return Month.of(selectedMonth.value)
            .getDisplayName(TextStyle.FULL, Locale("da"))
            .replaceFirstChar { it.uppercase() }
    }

    fun ingredientClick() {
        inSeasonList
    }



}
