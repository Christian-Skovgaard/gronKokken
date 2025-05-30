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
import com.example.gronkokken.ui.pages.loadingscreen.LoadingScreen
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.gronkokken.ui.components.FirebaseImage

//Lukas

//composable that shows a loading screen while viewmodel is being initialized
@Composable
fun SeasonalVegetablesScreen(
    ingredientClick: (String) -> Unit,
    ingredientButtonText: String,
    arrowClick: () -> Unit,
    changeIngredientType: () -> Unit,
) {
    val viewmodel:SeasonalIngredientsViewmodel = viewModel()
    if (viewmodel.loading.value) {
        LoadingScreen()
    } else {
        SeasonalVegetablesList(ingredientClick, ingredientButtonText, arrowClick, changeIngredientType)
    }
}

@Composable
fun SeasonalVegetablesList(
    ingredientClick: (String) -> Unit,
    ingredientButtonText: String,
    arrowClick: () -> Unit,
    changeIngredientType: () -> Unit
) {

    val viewmodel: SeasonalIngredientsViewmodel = viewModel()

    val ingredientsInSeason by viewmodel.inSeasonList

    //filters the ingredients in season, and only shows the vegetables
    val vegetablesOnly = ingredientsInSeason.filter { !it.isFruit }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F7FF))
            .padding(22.dp)
    ) {

        //header with button that goes from vegetable to fruit and from fruit to vegetable
        //month name, season name and slider that changes the month
        SeasonalIngredientsHeader(changeIngredientType, ingredientButtonText, arrowClick)

        //grid for database images
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalArrangement = Arrangement.spacedBy(50.dp)
        ) {
            //takes the amount of vegetables
            items(vegetablesOnly.size) { index ->
                val vegetable = vegetablesOnly[index]

                Column(
                    modifier = Modifier
                        //send id of vegetable clicked and navigates to ingredient screen
                        .clickable { ingredientClick(vegetable.id) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    FirebaseImage(
                        imagePath = "ingredienser/${vegetable.name.lowercase()}.jpg",
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(20.dp))
                    )


                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = vegetable.name)
                }


            }
        }
    }
}
