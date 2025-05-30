package com.example.gronkokken.models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import java.time.LocalDate

data class Recipe (  //Christian
    //constructoren skal have defaults eller være nullable, ellers kan firestore ikke bruge den.
    var id:String = "", //er var fordi den bliver opdateret efter objektet er lavet sidden det er metadata og ikke body
    val name:String = "",
    val flavorText:String = "",
    val ingredientsRaw:List<Map<String,String>> = listOf(), //Vi gemmer i Map fordi jeg har haft mange problemer med at gemme i custom classes i firestore.
    val instructions:String = "",
    val ratings:List<Int> = listOf(),
    val endDateRaw:String = "2025-05-28",
    val peopleAmount:Int = 1,
    val imagePath:String = "trio giraf.jpg"
) {
    val avgRating = ratings.average()

    var endDate:LocalDate = LocalDate.parse(endDateRaw)
    private val startDate:LocalDate = endDate.minusDays(7)

    //Den her string bruges til at vise ugen til brugeren.
    var weekDatesString = "${startDate.dayOfMonth}/${startDate.month} - ${endDate.dayOfMonth}/${endDate.month}"


    var ingredients: MutableList<RecipeIngredient> = mutableListOf()


    init {
        ingredientsRaw.forEach { ingredient ->
            ingredients.add(
                RecipeIngredient(
                    name = ingredient["name"].toString(),   //vi kunne gøre dem nullable for at slippe for Type-convertion, men så skulle vi deale med det senere når det skulle displayes
                    amount = mutableStateOf(ingredient["amount"].toString().toIntOrNull()), //sidden null ikke kan laves toInt(), bruges toIntOrNull()
                    amountUnit = ingredient["amountUnit"].toString()
                )
            )
        }
    }

    fun initLogic () {
        //det her kode gør det samme som init-blokken, men siden firestore bruger
        // reflection til at indsætte data i klassen bliver initblokken ikke kørt når vi bruger
        // .toObject(), derfor har vi brug for den her funktion til at køre efter et recipe-objekt
        // er blevet hentet fra firebase.
        // init-blokken er der stadig i tilfælde af at objektet bliver skabt organisk
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
        endDate = LocalDate.parse(endDateRaw)
    }

    override fun toString(): String {
        return "Recipe(name='$name, ingredientsRaw=$ingredientsRaw, ingredients=$ingredients, ratings=$ratings')"
    }
}