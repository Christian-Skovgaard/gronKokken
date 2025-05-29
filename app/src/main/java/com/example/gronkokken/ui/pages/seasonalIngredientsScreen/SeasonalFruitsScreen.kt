package com.example.gronkokken.ui.pages.seasonalIngredientsScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.gronkokken.ui.components.FirebaseImage
import com.example.gronkokken.ui.pages.loadingscreen.LoadingScreen

//Lukas

//composable that shows a loading screen while viewmodel is being initialized
@Composable
fun SeasonalFruitsScreen(
    ingredientClick: (String) -> Unit,
    ingredientButtonText: String,
    arrowClick: () -> Unit,
    changeIngredientType: () -> Unit,
) {
    val viewmodel:SeasonalIngredientsViewmodel = viewModel()
    if (viewmodel.loading.value) {
        LoadingScreen()
    } else {
        SeasonalFruitsList(ingredientClick, ingredientButtonText, arrowClick, changeIngredientType)
    }
}

@Composable
fun SeasonalFruitsList(
    ingredientClick: (String) -> Unit,
    ingredientButtonText: String,
    arrowClick: () -> Unit,
    changeIngredientType: () -> Unit
) {
    val viewmodel: SeasonalIngredientsViewmodel = viewModel()

    val ingredientsInSeason by viewmodel.inSeasonList

    //filters the ingredients in season, and only shows the fruit
    val fruitsOnly = ingredientsInSeason.filter { it.isFruit }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F7FF))
            .padding(22.dp)
    ) {
        //header with button that goes from vegetable to fruit and back
        SeasonalIngredientsHeader(changeIngredientType, ingredientButtonText, arrowClick)

        //grid for database images
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F7FF)),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            items(fruitsOnly.size) { index ->
                val fruit = fruitsOnly[index]
                    Column(
                        modifier = Modifier
                            .clickable {
                                ingredientClick(fruit.id)
                                Log.d("nav", "Clicked on fruit with id: ${fruit.id}")
                                       },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

                    ) {
                        FirebaseImage(
                            imagePath = "ingredienser/${fruit.name.lowercase()}.jpg",
                            modifier = Modifier
                                .size(110.dp)
                                .clip(RoundedCornerShape(20.dp)),
                        )

                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = fruit.name)
                    }
                }

        }
    }
}