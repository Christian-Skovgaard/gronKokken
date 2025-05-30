package com.example.gronkokken.models

import androidx.compose.runtime.MutableState

data class RecipeIngredient(    //Christian
    val name: String?,
    val amount: MutableState<Int?>,
    val amountUnit: String?
    //evt en referance til ingredient screen med mere info
) {
    override fun toString(): String {   //bliver ikke brugt til andet end debugging, så hvis nogen vil ændre/bruge til noget er de velkomne:D
        return "RecipeIngredient(name=$name, amount=$amount, amountUnit=$amountUnit)"
    }
}