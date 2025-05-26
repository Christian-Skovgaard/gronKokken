package com.example.gronkokken.ui.pages.frontpage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.gronkokken.com.example.gronkokken.ui.pages.Frontpage.FrontPageScreen
import com.example.gronkokken.com.example.gronkokken.ui.pages.Frontpage.FrontPageTeacherScreen
import com.example.gronkokken.repository.UserViewModel

@Composable
fun FrontPageTest (userViewModel: UserViewModel, navHostController: NavHostController) {
    val role by userViewModel.role // fx: "lærer" eller "elev"

    if (role == UserViewModel.Role.Teacher) {
        // Viser lærerens knapper
        FrontPageTeacherScreen(navHostController)
    } else {
        FrontPageScreen(navHostController)
    }
}
