package com.example.gronkokken.dataclasses

data class RecipeIngredient (   //Christian
    val name:String,
    val amount:Double,
    val amountUnit:String,
    //evt valgfri reference til ingredient screen, som virker som link
) {
    override fun toString(): String {
        return "name='$name',amount=$amount,amountUnit='$amountUnit'"
    }

}