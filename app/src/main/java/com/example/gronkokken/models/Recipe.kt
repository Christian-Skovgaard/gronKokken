package com.example.gronkokken.models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import java.time.LocalDate

data class Recipe (  //Christian
    //constructoren skal have defaults eller være nullable, ellers kan firestore ikke bruge den.
    var id:String = "", //er var fordi den bliver opdateret efter objektet er lavet sidden det er metadata og ikke body
    val name:String = "",
    val flavorText:String = "",
    val ingredientsRaw:List<Map<String,String>> = listOf(),    //vi gemmer i map da jeg før har haft mange problemer med at gemme custom types i firestore, og når classen ikke er mere kompiceret er det her nemmest
    val instructions:String = "",
    val ratings:List<Int> = listOf(),
    val endDateRaw:String = "2025-05-28",
    val peopleAmount:Int = 1
    //form for billedliste
) {
    val avgRating = ratings.average()

    val endDate:LocalDate = LocalDate.parse(endDateRaw)
    private val startDate:LocalDate = endDate.minusDays(7)

    //Den her string bruges til at vise ugen til brugeren.
    var weekDatesString = "${startDate.dayOfMonth}/${startDate.month} - ${endDate.dayOfMonth}/${endDate.month}"


    var ingredients: MutableList<RecipeIngredient> = mutableListOf()

        /*
        ingredientsRaw.map { ingredient -> RecipeIngredient(
        name = ingredient["name"].toString(),   //vi kunne gøre dem nullable for at slippe for Type-convertion, men så skulle vi deale med det senere når det skulle displayes
        amount = ingredient["amount"].toString().toIntOrNull(), //sidden null ikke kan laves toInt(), bruges toIntOrNull()
        amountUnit = ingredient["amountUnit"].toString()
    ) }
         */

    init {
        Log.d("lookmom","ingredient raw size on construction = " + ingredientsRaw.size.toString())
        ingredientsRaw.forEach { ingredient ->
            Log.d("lookmom",ingredient.toString())
            ingredients.add(
                RecipeIngredient(
                    name = ingredient["name"].toString(),   //vi kunne gøre dem nullable for at slippe for Type-convertion, men så skulle vi deale med det senere når det skulle displayes
                    amount = mutableStateOf(ingredient["amount"].toString().toIntOrNull()), //sidden null ikke kan laves toInt(), bruges toIntOrNull()
                    amountUnit = ingredient["amountUnit"].toString()
                )
            )
        }
        //Log.d("lookmom",ingredients.toString() + "list log")
    }

    override fun toString(): String {
        return "Recipe(name='$name, ingredientsRaw=$ingredientsRaw, ingredients=$ingredients, ratings=$ratings')"
    }
}