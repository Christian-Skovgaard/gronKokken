package com.example.gronkokken

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gronkokken.repository.UserViewModel
import com.example.gronkokken.ui.pages.landingpage.Landingpage
import com.example.gronkokken.ui.pages.frontpage.FrontPageTest
import com.example.gronkokken.ui.pages.Register.RegisterPage
import com.example.gronkokken.ui.pages.landingpage.LandingpageTeacher
import com.example.gronkokken.ui.pages.login.LoginPage

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