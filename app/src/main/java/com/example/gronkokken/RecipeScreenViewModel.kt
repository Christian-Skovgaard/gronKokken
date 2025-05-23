package com.example.gronkokken

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gronkokken.components.Firestore
import com.example.gronkokken.components.InternalStorage
import com.example.gronkokken.models.Recipe
import kotlinx.coroutines.launch

class RecipeScreenViewModel(savedStateHandle: SavedStateHandle): ViewModel() {  //Christian
    val recipeID:String = savedStateHandle["recipeId"] ?: ""

    val fireStore = Firestore()

    var loading: MutableState<Boolean> = mutableStateOf(true)  //default skal være true

    var recipe:Recipe = Recipe()

    //val internalStorage = InternalStorage()

    //ingredientCheckbox
    val ingredientCheckboxState: MutableList<MutableState<Boolean>> = mutableStateListOf()
    fun onIngredientCheckboxClick (i:Int) {
        ingredientCheckboxState[i].value = !ingredientCheckboxState[i].value
    }

    //ratingSystem
    val ratingStarList:MutableList<MutableState<String>> = MutableList(5) {mutableStateOf("☆")} //den laver en liste med size 5 og tilføjer det i lamda til hver
    fun updateRating (index:Int) {
        for (j in 0..ratingStarList.size-1) {
            if (index >= j) {ratingStarList[j].value = "★"}
            else {ratingStarList[j].value = "☆"}
        }
    }

    init {
        Log.d("lookmom","started recipeScreen")
        viewModelScope.launch {
            recipe = fireStore.getRecipeById(recipeID)
            recipe.toString()
            for(i in 1..recipe.ingredients.size) {
                ingredientCheckboxState.add(mutableStateOf(false))
            }
            loading.value = false
            Log.d("lookmom","ended fetch")
        }

    }

    override fun onCleared() {  //function som kører når man lukker viewmodlen, ligesom init kører når man starter.
        super.onCleared()
        //kald med bedømmelse til db
    }
/*
    //Recipe til testing uden db-call hvis nogen skal bruge:D
    recipe = Recipe(
    name = "Cannelloni med svampe og spinat",
    flavorText = "Den lækre italienske ret med masser af smag og fyld. Server eventuelt en grøn salat ved siden af",
    ingredientsRaw = listOf(
    mapOf(
    "name" to "tomater mosede eller hakkede",
    "amount" to "800",
    "amountUnit" to "g"),
    mapOf(
    "name" to "olivenolie",
    "amount" to "3",
    "amountUnit" to "spsk."),
    mapOf(
    "name" to "hvidløg Presset",
    "amount" to "2",
    "amountUnit" to "fed")
    ),
    instructions = "Tænd ovnen på 200 grader, og smør et ovnfast fad med olie." +
    "2" +
    "Tomatsauce: Bland tomat, olivenolie, hvidløg og basilikum. Blend igennem med en stavblender. Rør kapers i og smag til med sukker, salt og peber.",
    ratings = listOf(),
    endDateRaw = "2025-06-01",
    peopleAmount = 4
    )
 */
}