package com.example.gronkokken

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.gronkokken.com.example.gronkokken.ui.theme.FrontPageTeacherScreen
import com.example.gronkokken.components.Navigation
import com.example.gronkokken.components.UserViewModel
import com.example.gronkokken.recipeListScreen.RecipeListScreen
import com.example.gronkokken.ui.theme.GronKokkenTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontPageTest()
            val userViewModel: UserViewModel = viewModel()
            val navController = rememberNavController()



        }
    }
}
@Composable
fun FrontPageTest (userViewModel: UserViewModel = viewModel()) {
    val role = userViewModel.role // fx: "lærer" eller "elev"
    Text("hey")

    val isTeacher = false

    if (isTeacher) {
        // Viser lærerens knapper
        FrontPageTeacherScreen()
    } else {
        FrontPageScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    GronKokkenTheme {
        FrontPageScreen()
    }
}