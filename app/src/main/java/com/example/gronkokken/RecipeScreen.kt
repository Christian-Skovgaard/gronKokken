package com.example.gronkokken

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gronkokken.dataclasses.RecipeIngredient
import kotlin.math.max

@Composable
fun RecipeScreen () {
    val viewModel:RecipeScreenViewModel = viewModel();
    if (viewModel.loading.value) {LoadingScreen()}
    else if (!viewModel.loading.value) {
        RecipeViewScreen(
            mainTitleText = viewModel.recipe.name,
            ingredientList = viewModel.recipe.ingredients,
            ingredientCheckStateList = viewModel.ingredientCheckboxState,
            ingredientOnCheckboxClick = viewModel::onIngredientCheckboxClick,
            instructionText = viewModel.recipe.instructions,
            ratingStarList = viewModel.ratingStarList,
            onRatingStarClick = viewModel::updateRating
        )
    }

}


@Composable
fun RecipeViewScreen (
    mainTitleText: String,
    ingredientList: List<RecipeIngredient>,
    ingredientCheckStateList: MutableList<MutableState<Boolean>>,
    ingredientOnCheckboxClick:(Int)->Unit,
    instructionText:String,
    ratingStarList:List<MutableState<String>>,
    onRatingStarClick:(index:Int)->Unit
) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 20.dp)
    ) {
        RecipeMainTitle(mainTitleText)
        //image
        RecipeSubTitle("Ingredienser")
        RecipeIngredientsList(ingredientList,ingredientCheckStateList,ingredientOnCheckboxClick)
        RecipeSubTitle("sådan gør du")
        RecipeInstructions(instructionText)
        RecipeRatingTitle()
        RecipeRating(ratingStarList,onRatingStarClick)
        //review
    }
}

@Composable
fun RecipeMainTitle (mainTitleText:String) {
    Box (
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = mainTitleText,
            color = Color(0xFF4B7A2B),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun RecipeSubTitle (subTitleText:String) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = subTitleText,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun RecipeIngredientsList (
    ingredientList: List<RecipeIngredient>,
    ingredientCheckStateList: MutableList<MutableState<Boolean>>,
    ingredientOnCheckboxClick:(Int)->Unit
) {
    Box(modifier = Modifier) {
    LazyColumn {
        items(max(ingredientList.size,ingredientCheckStateList.size)) { i ->
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
                    .border(1.dp, Color.Red)
                ) {
                Row {
                    Row (
                        modifier = Modifier.fillMaxWidth(0.2f),

                    ) {
                        Text(
                            text = ingredientList[i].amount.toString(),  //vi bruger toSting() fordi den er nullable
                            modifier = Modifier
                                .fillMaxWidth(0.4f)
                                .padding(horizontal = 2.dp),
                            textAlign = TextAlign.End

                        )
                        Text(
                            text = ingredientList[i].amountUnit.toString()
                        )
                    }
                    Text(
                        text = ingredientList[i].name.toString()
                    )
                }
                    Checkbox(
                        checked = ingredientCheckStateList[i].value,
                        onCheckedChange = { ingredientOnCheckboxClick(i) },
                        modifier = Modifier.padding(0.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeInstructions (instructionText:String) {
    Box() {
        Text(
            text = instructionText
        )
    }
}

@Composable
fun RecipeRatingTitle () {
    Box (
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center

    ){
        Text(
            text = "Bedømmelse",
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun RecipeRating (ratingStarList:List<MutableState<String>>,onRatingStarClick:(index:Int)->Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        (items(5) {i ->
            Box (
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable{onRatingStarClick(i)}
            ){
                Text(
                    text = ratingStarList[i].value,
                    fontSize = 40.sp,
                    color = Color.Yellow
                )
            }
        })
    }
}






