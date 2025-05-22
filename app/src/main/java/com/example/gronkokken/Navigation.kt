package com.example.gronkokken

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gronkokken.repository.UserViewModel
import com.example.gronkokken.ui.pages.landingpage.Landingpage
import com.example.gronkokken.ui.pages.recipeListScreen.RecipeListScreen
import com.example.gronkokken.ui.pages.Frontpage.FrontPageTest

@Composable
fun Navigation (navHostController: NavHostController, userViewModel: UserViewModel) {
    NavHost(navHostController,startDestination = "landingpage") {
        //composable("recipeListScreen") {
        //    RecipeListScreen()
        //}
        composable("landingpage") {
            Landingpage(userViewModel,
                studentButtonClick = {
                    navHostController.navigate("frontpage")
            })
        }
        composable("frontpage") {
            FrontPageTest(userViewModel)
        }
    }
}