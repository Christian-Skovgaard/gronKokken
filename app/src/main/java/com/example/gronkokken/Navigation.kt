package com.example.gronkokken

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gronkokken.recipeListScreen.RecipeListScreen
import com.example.gronkokken.repository.UserViewModel
import com.example.gronkokken.ui.pages.landingpage.Landingpage
import com.example.gronkokken.ui.pages.frontpage.FrontPageTest
import com.example.gronkokken.ui.pages.Register.RegisterPage
import com.example.gronkokken.ui.pages.landingpage.LandingpageTeacher
import com.example.gronkokken.ui.pages.login.LoginPage
import com.example.gronkokken.ui.pages.recipescreen.RecipeScreen

@Composable
fun Navigation (navHostController: NavHostController, userViewModel: UserViewModel) {
    NavHost(navHostController,startDestination = "landingpage") {
        fun recipeNavigateById (recipeId:String):Unit { //Christian
            navHostController.navigate("recipe/$recipeId")
        }
        composable("recipeListScreen") {
            RecipeListScreen(::recipeNavigateById)
        }
        composable( //Christian
            route = "recipe/{recipeId}",
            arguments = listOf(navArgument(name = "recipeId") { type = NavType.StringType })
        ) {
            RecipeScreen()
        }
        composable("landingpage") {
            Landingpage(userViewModel,
                studentButtonClick = {
                    navHostController.navigate("frontpage")
            },
                teacherButtonClick = {
                    navHostController.navigate("landingpage-teacher")
                },
                guestButtonClick = {
                    navHostController.navigate("frontpage")
                }

            )
        }
        composable("landingpage-teacher") {
            LandingpageTeacher(
                userViewModel,
                loginButtonClick = {
                    navHostController.navigate("loginpage")
                },
                registerButtonClick = {
                    navHostController.navigate("registerpage")
                },
                onBackArrowClick = {
                    navHostController.popBackStack()
                }
            )
        }
        composable("frontpage") {
            FrontPageTest(userViewModel)
        }
        composable("loginpage") {
            LoginPage(
                onButtonClick = {
                navHostController.navigate("frontpage")
            },
                onBackArrowClick = {
                    navHostController.popBackStack()
                }
            )
        }
        composable("registerpage") {
            RegisterPage(
                onClick = {
                    navHostController.navigate("frontpage")
                },
                onBackArrowClick = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}