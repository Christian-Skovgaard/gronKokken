package com.example.gronkokken

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.gronkokken.com.example.gronkokken.ui.pages.Frontpage.FrontPageScreen
import com.example.gronkokken.com.example.gronkokken.ui.pages.Frontpage.FrontPageTeacherScreen
import com.example.gronkokken.repository.UserViewModel
import com.example.gronkokken.ui.theme.GronKokkenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val userViewModel: UserViewModel = viewModel()
            val navController = rememberNavController()
            Navigation(navHostController = navController, userViewModel)


        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    GronKokkenTheme {
        FrontPageScreen()
    }
}