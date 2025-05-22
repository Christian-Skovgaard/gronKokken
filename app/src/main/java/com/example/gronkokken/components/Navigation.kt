package com.example.gronkokken.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gronkokken.RecipeScreen
import com.example.gronkokken.recipeListScreen.RecipeListScreen

@Composable
fun Navigation (navHostController: NavHostController) {
    fun recipeNavigateById (recipeId:String):Unit {
        navHostController.navigate("recipe/$recipeId")
    }

    NavHost(navHostController,startDestination = "recipeListScreen") {
        composable("recipeListScreen") {
            RecipeListScreen(::recipeNavigateById)
        }
        composable(
            route = "recipe/{recipeId}",
            arguments = listOf(navArgument(name = "recipeId") { type = NavType.StringType })
        ) {
            RecipeScreen()
        }
    }

}