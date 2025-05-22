package com.example.gronkokken.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gronkokken.RecipeScreen
import com.example.gronkokken.dataclasses.Recipe
import com.example.gronkokken.recipeListScreen.RecipeListScreen

@Composable
fun Navigation (navHostController: NavHostController) {
    NavHost(navHostController,startDestination = "recipeListScreen") {
        composable("recipeListScreen") {
            RecipeListScreen()
        }
        composable("recipe") {
            RecipeScreen()
        }
    }
}