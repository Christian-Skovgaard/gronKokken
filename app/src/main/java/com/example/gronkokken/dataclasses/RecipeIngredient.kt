package com.example.gronkokken.dataclasses

import androidx.compose.runtime.MutableState

data class RecipeIngredient(    //Christian
    val name: String?,
    val amount: MutableState<Int?>,
    val amountUnit: String?
    //evt en referance til ingredient screen med mere info
)