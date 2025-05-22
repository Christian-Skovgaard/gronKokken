package com.example.gronkokken

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gronkokken.ui.pages.recipeListScreen.RecipeListScreen

@Composable
fun Navigation (navHostController: NavHostController) {
    NavHost(navHostController,startDestination = "") {
        composable("recipeListScreen") {
            RecipeListScreen()
        }
    }
}