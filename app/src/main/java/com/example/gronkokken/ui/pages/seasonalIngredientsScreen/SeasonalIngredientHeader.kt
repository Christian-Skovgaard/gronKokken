package com.example.gronkokken.ui.pages.seasonalIngredientsScreen

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

@Composable
fun SeasonalIngredientsHeader(changeIngredientType: () -> Unit, ingredientButtonText: String, arrowClick: () -> Unit) {

    val viewmodel: SeasonalIngredientsViewmodel = viewModel()

    val selectedMonth by viewmodel.selectedMonth

    val season = when (selectedMonth) {
        in 3..5 -> "Forår"
        in 6..8 -> "Sommer"
        in 9..11 -> "Efterår"
        else -> "Vinter"
    }



    Column(

    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
            contentDescription = "",
            modifier = Modifier
                .clickable { arrowClick() }
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
                text = viewmodel.getSelectedMonthName(),
                fontSize = 20.sp,
                color = Color(0xFF4B7A2B)
            )
        }

        MonthSlider(viewmodel)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(start = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { changeIngredientType() },
                modifier = Modifier
                    .offset(0.dp, 35.dp)
                    .width(140.dp)
                    .height(40.dp),
                border = BorderStroke(2.dp, Color(0xFF121212)),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF8F7FF),
                    contentColor = Color(0xff121212)
                )
            ) {
                Text(
                    text = ingredientButtonText,
                    fontSize = 14.sp,
                    color = Color(0xFF4B7A2B)
                )
            }
        }
    }
    }