package com.example.gronkokken.recipeListScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.style.TextDecoration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gronkokken.models.Recipe
import com.example.gronkokken.repository.Firestore
import kotlinx.coroutines.launch
import java.time.LocalDate

class RecipeListScreenViewModel:ViewModel() {   //Christian
    var loading: MutableState<Boolean> = mutableStateOf(true)
    var showingCurrentRecipes:MutableState<Boolean> = mutableStateOf(true)

    private val firestore = Firestore()

    var completeRecipeList:List<Recipe> = listOf()

    val currentRecipeList:MutableList<Recipe> = mutableListOf()
    val previousRecipeList:MutableList<Recipe> = mutableListOf()

    var leftTextButtonUnderline:TextDecoration = TextDecoration.Underline
    var rightTextButtonUnderline:TextDecoration = TextDecoration.None

    init {
        viewModelScope.launch {
            val importList = firestore.getAllRecipes()
            completeRecipeList = importList

            completeRecipeList.forEach {
                if(it.endDate.isAfter(LocalDate.now())) {    //tjekker om opskriftens dag er efter dagen i dag.
                    currentRecipeList.add(it)
                }
                else if(it.endDate.isBefore(LocalDate.now())) {
                    previousRecipeList.add(it)
                }
            }

            loading.value = false
        }
    }


    //Vi skal bruge 2 funktioner i steddet for en som flipper, så den ikke skifter hvis man trykker på samme knap to gange.
    fun leftTextButtonOnClick () {
        showingCurrentRecipes.value = true
        leftTextButtonUnderline = TextDecoration.Underline
        rightTextButtonUnderline = TextDecoration.None
    }

    fun rightTextButtonOnClick () {
        showingCurrentRecipes.value = false
        rightTextButtonUnderline = TextDecoration.Underline
        leftTextButtonUnderline = TextDecoration.None
    }
}
