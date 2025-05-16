package com.example.gronkokken.dataclasses

data class Recipe (  //Christian
    val name:String,
    val flavorText:String,
    private val ingredientsRaw:List<Map<String,String>>,    //vi gemmer i map da jeg før har haft mange problemer med at gemme custom types i firestore, og når classen ikke er mere kompiceret er det her nemmest
    val instructions:String,
    private val ratings:List<Int>,
    val weekNr:Int,
    val peopleAmount:Int
    //form for billedliste
) {
    val avgRating = ratings.average()

    val ingredients:List<RecipeIngredient> = ingredientsRaw.map { ingredient -> RecipeIngredient(
        name = ingredient["name"].toString(),   //vi kunne gøre den nullable for at slippe for Type-convertion, men så skulle vi deale med det senere når det skulle displayes
        amount = ingredient["amount"].toString().toInt(),
        amountUnit = ingredient["amountUnit"].toString()
    ) }
}