package com.example.gronkokken.ui.pages.seasonalIngredientsScreen

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gronkokken.models.SeasonalIngredient

//Lukas

@Composable
fun SeasonalIngredientsScreen() {
    val viewmodel:SeasonalIngredientsViewmodel = viewModel()

    val ingredients by viewmodel.ingredientsList

    LaunchedEffect(ingredients) {
        Log.d("yahoo", "Ingredients updated: $ingredients")
    }



}