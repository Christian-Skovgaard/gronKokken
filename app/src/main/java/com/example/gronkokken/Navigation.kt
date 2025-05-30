package com.example.gronkokken

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gronkokken.com.example.gronkokken.ui.pages.UploadScreen
import com.example.gronkokken.recipeListScreen.RecipeListScreen
import com.example.gronkokken.repository.UserViewModel
import com.example.gronkokken.ui.pages.ClimaPlanScreen
import com.example.gronkokken.ui.pages.landingpage.Landingpage
import com.example.gronkokken.ui.pages.frontpage.FrontPageTest
import com.example.gronkokken.ui.pages.Register.RegisterPage
import com.example.gronkokken.ui.pages.landingpage.LandingpageTeacher
import com.example.gronkokken.ui.pages.login.LoginPage
import com.example.gronkokken.ui.pages.recipescreen.RecipeScreen
import com.example.gronkokken.ui.pages.seasonalIngredientsScreen.IngredientScreen
import com.example.gronkokken.ui.pages.seasonalIngredientsScreen.SeasonalFruitsScreen
import com.example.gronkokken.ui.pages.seasonalIngredientsScreen.SeasonalIngredientsViewmodel
import com.example.gronkokken.ui.pages.seasonalIngredientsScreen.SeasonalVegetablesScreen

@Composable
fun Navigation (navHostController: NavHostController, userViewModel: UserViewModel) {
    NavHost(navHostController,startDestination = "landingpage") {
        fun recipeNavigateById (recipeId:String) { //Christian
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
            FrontPageTest(
                userViewModel,
                navHostController = navHostController
            )
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
                    navHostController.navigate("loginpage")
                },
                onBackArrowClick = {
                    navHostController.popBackStack()
                }
            )
        }
        composable("seasonal-vegetables") {
            SeasonalVegetablesScreen(
                ingredientClick = { ingredientId ->
                    navHostController.navigate("ingredient/$ingredientId")
                },
                ingredientButtonText = "Se frugter",
                arrowClick = {
                    navHostController.navigate("frontpage")
                },
                changeIngredientType = {
                    navHostController.navigate("seasonal-fruits")
                }
            )
        }
        composable("seasonal-fruits") {
            SeasonalFruitsScreen(
                ingredientClick = { ingredientId ->
                    navHostController.navigate("ingredient/$ingredientId")
                },
                ingredientButtonText = "Se grøntsager",
                arrowClick = {
                    navHostController.navigate("frontpage")
                },
                changeIngredientType = {
                    navHostController.navigate("seasonal-vegetables")
                }
            )
        }
        composable(
            "ingredient/{ingredientId}",
            arguments = listOf(navArgument("ingredientId") {type = NavType.StringType})
        ) { backStackEntry ->
            val ingredientId = backStackEntry.arguments?.getString("ingredientId") ?: return@composable
            IngredientScreen(ingredientId, arrowClick = {
                navHostController.popBackStack()
            })

        }
        composable("climate") {
            ClimaPlanScreen(        //hvad er det for et navn, lol
                userViewModel,
                navHostController)
        }
        composable("mineUpload") {
            UploadScreen(
                backButtonOnClick = { navHostController.popBackStack() }
            )
        }
    }
}