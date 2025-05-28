package com.example.gronkokken.ui.pages.seasonalIngredientsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun MonthSlider(viewModel: SeasonalIngredientsViewmodel) {
    var sliderValue by remember { mutableStateOf(viewModel.selectedMonth.value.toFloat()) }

    Column {
        Slider(
            value = sliderValue,
            onValueChange = {
                sliderValue = it
                viewModel.setSelectedMonth(it.toInt())
            },
            valueRange = 1f..12f,
            steps = 10
        )
    }
}