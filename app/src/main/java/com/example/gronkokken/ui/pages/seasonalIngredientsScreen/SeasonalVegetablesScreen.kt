package com.example.gronkokken.ui.pages.seasonalIngredientsScreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gronkokken.R
import com.example.gronkokken.ui.components.FirebaseImage
import com.example.gronkokken.ui.pages.loadingscreen.LoadingScreen

//Lukas

@Composable
fun SeasonalVegetablesScreen(ingredientClick: () -> Unit, ingredientButtonText: String) {
    val viewmodel:SeasonalIngredientsViewmodel = viewModel()
    if (viewmodel.loading.value) {
        LoadingScreen()
    } else {
        SeasonalVegetablesList(ingredientClick, ingredientButtonText)
    }
}

@Composable
fun SeasonalVegetablesList(ingredientClick: () -> Unit, ingredientButtonText: String) {

    val viewmodel: SeasonalIngredientsViewmodel = viewModel()

    val ingredientsInSeason by viewmodel.inSeasonList

    val vegetablesOnly = ingredientsInSeason.filter { !it.isFruit }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F7FF))
            .padding(22.dp)
    ) {
        SeasonalIngredientsHeader(ingredientClick, ingredientButtonText)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalArrangement = Arrangement.spacedBy(50.dp)
        ) {
            items(vegetablesOnly.size) { index ->
                val vegetable = vegetablesOnly[index]

                Column(
                    modifier = Modifier
                        .clickable { },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    FirebaseImage(
                        imagePath = "${vegetable.name}.jpg",
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(20.dp))
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = vegetable.name)
                }


            }
        }
    }
}
