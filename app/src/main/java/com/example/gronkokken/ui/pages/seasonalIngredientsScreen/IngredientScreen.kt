package com.example.gronkokken.ui.pages.seasonalIngredientsScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.gronkokken.R
import com.example.gronkokken.ui.components.FirebaseImage
import com.example.gronkokken.ui.pages.loadingscreen.LoadingScreen

//Lukas

@Composable
fun IngredientScreen(ingredientId: String?, arrowClick: () -> Unit) {
    val viewmodel: SeasonalIngredientsViewmodel = viewModel()

    //using find to match the id with the one that was clicked
    val ingredient = viewmodel.ingredientsList.value.find { it.id == ingredientId }

    Log.d("screen", "IngredientId: $ingredientId")
    Log.d("screen", "Available IDs: ${viewmodel.ingredientsList.value.map { it.id }}")

    //show loadingscreen if the ingredients havent loaded
    if (viewmodel.ingredientsList.value.isEmpty()) {
        LoadingScreen()
    } else if (ingredient != null) {
        //back arrow
        Icon(
            painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
            contentDescription = "",
            modifier = Modifier
                .padding(25.dp)
                .clickable { arrowClick() }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //ingredient name with viewmodel
            Text(
                text = ingredient.name,
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4B7A2B)
            )
            Spacer(modifier = Modifier.height(10.dp))
            //box so the text can sit over the bottom of the picture
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                //image from firebase
                FirebaseImage(
                    imagePath = "ingredienser/${ingredient.name.lowercase()}.jpg",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                )


                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomCenter)
                        .padding(top = 200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(2.dp, Color(0xFF121212), shape = RoundedCornerShape(16.dp))
                        .background(Color(0xFFF8F7FF))
                        .padding(16.dp)

                ) {
                    //description with viewmodel
                    Text(
                        text = ingredient.description
                    )
                }
            }
        }

    } else {
        Text("fejl")

    }
}