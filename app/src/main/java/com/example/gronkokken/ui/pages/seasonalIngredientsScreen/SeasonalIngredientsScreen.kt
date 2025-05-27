package com.example.gronkokken.ui.pages.seasonalIngredientsScreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gronkokken.R
import com.example.gronkokken.models.SeasonalIngredient
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
            .padding(16.dp)
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
                text = season
            )
            Text(
                text = "Juni"
            )
        }
        ingredientsInSeason.forEach {
            Text(it.name)
        }


    }



}

