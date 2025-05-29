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



//Lukas with a little chatgpt

class SeasonalIngredientsViewmodel(): ViewModel() {

    //use firestore
    private val fireStore = Firestore()

    //value that is used to show a loadingscreen. by default it is true, and is changed to false
    //after init has finished
    var loading: MutableState<Boolean> = mutableStateOf(true)

    //mutable list for all ingredients, that is changed only in this viewmodel
    private val _ingredientsList = mutableStateOf<List<SeasonalIngredient>>(emptyList())
    //read only list for ui to show
    val ingredientsList: State<List<SeasonalIngredient>> = _ingredientsList

    //mutable state list with ingredients in season. this value is changed in the init to show
    //the the ingredients in season for the current month, but is also changed by
    //setSelectedMonth function that changes the month
    private val _inSeasonList = mutableStateOf<List<SeasonalIngredient>>(emptyList())
    //read only state list for ui
    val inSeasonList: State<List<SeasonalIngredient>> = _inSeasonList

    //current month
    var selectedMonth = mutableStateOf(LocalDate.now().monthValue)
        private set

    init {
        //viewModelScope to get ingredients from firebase and put them into the different variables
        //asynchronously
        viewModelScope.launch {
            //get ingredients
            val importList = fireStore.getSeasonalIngredients()
            val currentMonth  = LocalDate.now().monthValue

            _ingredientsList.value = importList
            //filter ingredients only for current month
            _inSeasonList.value = importList.filter { it.isInSeason(currentMonth) }

            //remove loading screen
            loading.value = false

        }
    }

    fun getCurrentMonth(): String {
        val currentMonth = LocalDate.now().monthValue

        val currentMonthName = Month.entries[currentMonth - 1]

        return currentMonthName.toString().lowercase().replaceFirstChar { it.uppercase() }
    }

    //get a month and make it the selected month
    //update inseasonlist to match with the month given
    fun setSelectedMonth(month: Int) {
        selectedMonth.value = month
        _inSeasonList.value = _ingredientsList.value.filter {
            it.isInSeason(month)
        }
    }

    //takes the number from selected month, and uses month of to get the month name that
    //correlates with the number. gets the full name in danish and makes the first letter
    //uppercase
    fun getSelectedMonthName(): String {
        return Month.of(selectedMonth.value)
            .getDisplayName(TextStyle.FULL, Locale("da"))
            .replaceFirstChar { it.uppercase() }
    }



}
