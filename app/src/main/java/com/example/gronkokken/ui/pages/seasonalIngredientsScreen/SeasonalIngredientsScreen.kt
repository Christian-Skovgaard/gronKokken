package com.example.gronkokken.ui.pages.seasonalIngredientsScreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gronkokken.R
import com.example.gronkokken.models.SeasonalIngredient
import com.example.gronkokken.ui.components.LandingButton
import com.example.gronkokken.ui.pages.loadingscreen.LoadingScreen
import java.time.LocalDate

//Lukas

@Composable
fun SeasonalIngredientsScreen() {
    val viewmodel:SeasonalIngredientsViewmodel = viewModel()
    if (viewmodel.loading.value) {
        LoadingScreen()
    } else {
        SeasonalIngredientList()
    }


}

@Composable
fun SeasonalIngredientList() {

    val viewmodel: SeasonalIngredientsViewmodel = viewModel()

    val ingredients by viewmodel.ingredientsList

    val ingredientsInSeason by viewmodel.inSeasonList

    val season = remember { viewmodel.getCurrentSeason() }

    /*Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Navn på første ingrediens: ${ingredients.getOrNull(0)?.name ?: "Ukendt"}")
        Text(text = "Beskrivelse af anden ingrediens: ${ingredients.getOrNull(1)?.endMonth ?: "Ukendt"}")
    }
    */

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(22.dp)
    ) {

        Icon(
            painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
            contentDescription = "",
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = season,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4B7A2B)
            )
            Text(
                text = viewmodel.getCurrentMonth(),
                fontSize = 20.sp,
                color = Color(0xFF4B7A2B)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Button(
                onClick = {},
                modifier = Modifier
                    .offset(0.dp, 35.dp)
                    .width(120.dp)
                    .height(40.dp),
                border = BorderStroke(2.dp, Color(0xFF121212)),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF8F7FF),
                    contentColor = Color(0xff121212)
                )
            ) {
                Text(
                    text = "Grøntsager",
                    fontSize = 14.sp,
                    color = Color(0xFF4B7A2B)
                )
            }
        }

        /*LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(ingredientsInSeason.size) { ingredient ->
                Text(
                    text = ingredientsInSeason[ingredient].name
                )
            }
        }*/

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalArrangement = Arrangement.spacedBy(50.dp)
        ) {
            items(ingredientsInSeason.size) { ingredient ->
                /*Text(
                    text = ingredientsInSeason[ingredient].name
                )*/

                Column(
                    modifier = Modifier
                        .clickable {  },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {
                    Image(
                        painter = painterResource(R.drawable.gulerod),
                        contentDescription = ingredientsInSeason[ingredient].name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(20.dp))

                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = ingredientsInSeason[ingredient].name)
                }
            }
        }



    }



}
