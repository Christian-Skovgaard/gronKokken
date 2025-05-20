package com.example.gronkokken.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.gronkokken.components.Navigation
import com.example.gronkokken.components.UserViewModel


class ClimaPlan : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GronKokkenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ClimaPlanScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
@Composable
fun ClimaPlanScreen(modifier: Modifier = Modifier) {
    val start by remember {mutableStateOf("Start Punkt")}
    val end by remember {mutableStateOf("Slut Punkt")}


    Column {
        
    }
}

