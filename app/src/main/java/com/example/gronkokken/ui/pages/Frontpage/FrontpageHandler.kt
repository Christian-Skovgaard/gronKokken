package com.example.gronkokken.ui.pages.Frontpage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gronkokken.com.example.gronkokken.ui.pages.Frontpage.FrontPageScreen
import com.example.gronkokken.com.example.gronkokken.ui.pages.Frontpage.FrontPageTeacherScreen
import com.example.gronkokken.repository.UserViewModel

@Composable
fun FrontPageTest (userViewModel: UserViewModel) {
    val role by userViewModel.role // fx: "lærer" eller "elev"

    if (role == UserViewModel.Role.Teacher) {
        // Viser lærerens knapper
        FrontPageTeacherScreen()
    } else {
        FrontPageScreen()
    }
}