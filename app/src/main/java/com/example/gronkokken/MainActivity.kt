package com.example.gronkokken

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.gronkokken.components.InternalStorage
import com.example.gronkokken.recipeListScreen.RecipeListScreen
import com.example.gronkokken.com.example.gronkokken.ui.pages.Frontpage.FrontPageScreen
import com.example.gronkokken.com.example.gronkokken.ui.pages.Frontpage.FrontPageTeacherScreen
import com.example.gronkokken.repository.UserViewModel
import com.example.gronkokken.ui.theme.GronKokkenTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val userViewModel: UserViewModel = viewModel()
            val navController = rememberNavController()

            GronKokkenTheme {
                Navigation(
                    navHostController = navController,
                    userViewModel = userViewModel
                )
            }
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GronKokkenTheme {
        Greeting("Android")
    }
}