package com.example.gronkokken.ui.pages.seasonalIngredientsScreen

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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gronkokken.ui.components.FirebaseImage
import com.example.gronkokken.ui.pages.loadingscreen.LoadingScreen

@Composable
fun SeasonalFruitsScreen(ingredientClick: () -> Unit, ingredientButtonText: String) {
    val viewmodel:SeasonalIngredientsViewmodel = viewModel()
    if (viewmodel.loading.value) {
        LoadingScreen()
    } else {
        SeasonalFruitsList(ingredientClick, ingredientButtonText)
    }
}

@Composable
fun SeasonalFruitsList(ingredientClick: () -> Unit, ingredientButtonText: String) {
    val viewmodel: SeasonalIngredientsViewmodel = viewModel()

    val ingredientsInSeason by viewmodel.inSeasonList

    val fruitsOnly = ingredientsInSeason.filter { it.isFruit }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(22.dp)
            .background(Color(0xFFF8F7FF))
    ) {
        SeasonalIngredientsHeader(ingredientClick, ingredientButtonText)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F7FF)),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalArrangement = Arrangement.spacedBy(50.dp)
        ) {
            items(fruitsOnly.size) { index ->
                val fruit = fruitsOnly[index]

                    Column(
                        modifier = Modifier
                            .clickable { },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

                    ) {
                        FirebaseImage(
                            imagePath = "${fruit.name}.jpg",
                            modifier = Modifier
                                .size(150.dp)
                                .clip(RoundedCornerShape(20.dp))
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = fruit.name)
                    }
                }

        }
    }
}