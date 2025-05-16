package com.example.gronkokken.recipeListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gronkokken.Firestore
import com.example.gronkokken.dataclasses.Recipe
import kotlinx.coroutines.launch

class RecipeListScreenViewModel:ViewModel() {
    var loading:Boolean = true  //skal nok have mutablestate

    private val firestore = Firestore()

    var recipeList:List<Recipe> = listOf()

    init {
        viewModelScope.launch {
            val importList = firestore.getAllRecipes()
            recipeList = importList
            //loading = false
        }
    }

}