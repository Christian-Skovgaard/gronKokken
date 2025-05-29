package com.example.gronkokken.ui.pages.seasonalIngredientsScreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.gronkokken.ui.components.FirebaseImage

@Composable
fun IngredientScreen(ingredientId: String?) {
    val viewmodel: SeasonalIngredientsViewmodel = viewModel()

    val ingredient = viewmodel.ingredientsList.value.find { it.id == ingredientId }

    Log.d("burgir", ingredient.toString())

    if (ingredient != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FirebaseImage(
                imagePath = "ingredienser/${ingredient.name.lowercase()}.jpg",
                modifier = Modifier
                    .size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = ingredient.name, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = ingredient.description)
        }

    } else {
        Text("fejl")

    }
}