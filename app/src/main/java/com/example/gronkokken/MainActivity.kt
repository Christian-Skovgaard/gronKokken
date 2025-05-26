package com.example.gronkokken

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
import com.example.gronkokken.components.Navigation
import com.example.gronkokken.components.UserViewModel
import com.example.gronkokken.recipeListScreen.RecipeListScreen
import com.example.gronkokken.ui.theme.ClimaPlanScreen
import com.example.gronkokken.ui.theme.GronKokkenTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClimaPlanScreen()
            val userViewModel: UserViewModel = viewModel()
            val navController = rememberNavController()

            FirebaseAuth.getInstance().signInAnonymously()
                .addOnSuccessListener {
                    Log.d("Auth", "Bruger logget ind anonymt: ${it.user?.uid}")
                }
                .addOnFailureListener {
                    Log.e("Auth", "Fejl ved anonym login", it)
                }

        }
    }
}

fun gemTilFirestore(startpunkt: String, slutpunkt: String) {
    val db = FirebaseFirestore.getInstance()
    val data = hashMapOf(
        "startpunkt" to startpunkt,
        "slutpunkt" to slutpunkt
    )

    db.collection("klimaplan")
        .document("bruger1") // Du kan ændre dette til f.eks. brugerens UID
        .set(data)
        .addOnSuccessListener { Log.d("Firestore", "Data gemt") }
        .addOnFailureListener { e -> Log.w("Firestore", "Fejl ved gemning", e) }
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