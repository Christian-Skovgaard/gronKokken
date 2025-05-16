package com.example.gronkokken.recipeListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gronkokken.dataclasses.Recipe

@Composable
fun RecipeListScreen () {
    val viewModel = RecipeListScreenViewModel()
    if (viewModel.loading) {
        LoadingScreen ()
    }
    else {
        RecipeDisplayList(viewModel.recipeList)
    }
}

@Composable
fun RecipeDisplayList (displayList:List<Recipe>) {
    LazyColumn {
        items(displayList.size) {
            displayList.forEach{
                RecipeDisplayBox(it.weekNr.toString(),it.name,it.flavorText)
            }
        }
    }
}

@Composable
fun RecipeDisplayBox (weekDisplay:String,titleDisplay:String,flavorTextDisplay:String) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .aspectRatio(3f)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Red)
            .padding(10.dp)
    ) {
        Row() {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.30f)
                    .aspectRatio(1f)
                    //.padding(5.dp)
                    .background(Color.Gray)
            ) {
                //image
            }
            Spacer(modifier = Modifier.size(width = 5.dp, height = 0.dp))
            Column {
                RecipeDisplayBoxWeek(weekDisplay)
                RecipeDisplayBoxTitle(titleDisplay)
                RecipeDisplayBoxFlavorText(flavorTextDisplay)
            }
        }
    }
}


@Composable
fun RecipeDisplayBoxWeek(weekDisplay:String) {
    Text(
        text = weekDisplay,
        fontSize = 12.sp,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun RecipeDisplayBoxTitle(titleDisplay:String) {
    Text(
        text = titleDisplay,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun RecipeDisplayBoxFlavorText(flavorTextDisplay:String) {
    Text(
        text = flavorTextDisplay,
        fontSize = 10.sp,
        modifier = Modifier.fillMaxWidth()
    )
}