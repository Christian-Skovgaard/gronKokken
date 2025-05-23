package com.example.gronkokken.ui.pages.recipeListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gronkokken.ui.pages.loadingscreen.LoadingScreen
import com.example.gronkokken.dataclasses.Recipe

@Composable
fun RecipeListScreen () {   //Christian
    val viewModel: RecipeListScreenViewModel = viewModel()
    if (viewModel.loading.value) {
        LoadingScreen ()
    }
    //man ville også have kunnet lavet listen mutablestate, men det virkede federe at flippe en boolian end at skulle erstatte hele listen.
    else if (viewModel.showingCurrentRecipes.value) {
        RecipeDisplayList(
            displayList = viewModel.currentRecipeList,
            rightButtonOnClick = viewModel::rightTextButtonOnClick,
            leftButtonOnClick = viewModel::leftTextButtonOnClick,
            leftButtonUnderline = viewModel.leftTextButtonUnderline,
            rightButtonUnderline = viewModel.rightTextButtonUnderline
        )
    }
    else if (!viewModel.showingCurrentRecipes.value) {
        RecipeDisplayList(
            displayList = viewModel.previousRecipeList,
            rightButtonOnClick = viewModel::rightTextButtonOnClick,
            leftButtonOnClick = viewModel::leftTextButtonOnClick,
            leftButtonUnderline = viewModel.leftTextButtonUnderline,
            rightButtonUnderline = viewModel.rightTextButtonUnderline
        )
    }
}



@Composable
fun RecipeDisplayList (displayList:List<Recipe>,leftButtonOnClick:()->Unit,rightButtonOnClick:()->Unit,leftButtonUnderline:TextDecoration,rightButtonUnderline:TextDecoration) {
    Column (){
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            RecipeDisplayTextButton("Aktuelle",leftButtonOnClick, leftButtonUnderline)
            RecipeDisplayTextButton("Tidligere",rightButtonOnClick, rightButtonUnderline)
        }
        Spacer(modifier = Modifier.size(16.dp))
        LazyColumn (

        ) {
            items(displayList.size) {
                displayList.forEach{
                    Box (
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        RecipeDisplayBox(it.weekDatesString, it.name, it.flavorText)
                    }
                }
            }
        }
    }
}

@Composable
fun RecipeDisplayTextButton (text:String, onClick:()->Unit, underline:TextDecoration) {
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .padding(35.dp)
    ) {
        Text(
            text = text,
            textDecoration = underline,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(0xFFA0ED6E)   //sidste 6 er hexkode
        )
    }
}

@Composable
fun RecipeDisplayBox (weekDisplay:String,titleDisplay:String,flavorTextDisplay:String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .aspectRatio(3f)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFA0ED6E))
            .padding(10.dp),

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
        fontSize = 18.sp,
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