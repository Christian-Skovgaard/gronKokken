package com.example.gronkokken.ui.pages.seasonalIngredientsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale
import kotlin.math.roundToInt

//Lukas

@Composable
fun MonthSlider(viewModel: SeasonalIngredientsViewmodel) {
    //value with state so we can update when using the slider
    //get the currentmonth and makes it into a float in this case 5f
    var sliderValue by remember { mutableStateOf(viewModel.selectedMonth.value.toFloat()) }

    Column {
        Slider(
            value = sliderValue,
            //using the slider recomposes the slider and updates the value
            //we update it by taking the value e.g. 3f and setting it to be the selected month
            onValueChange = {
                sliderValue = it
                viewModel.setSelectedMonth(it.roundToInt())
            },
            valueRange = 1f..12f,
            steps = 10,
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFF121212),
                activeTrackColor = Color(0xFF4B7A2B),
                inactiveTickColor = Color(0xAA121212)
            )
        )
    }
}