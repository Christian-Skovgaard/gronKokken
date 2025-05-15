package com.example.gronkokken.dataclasses

class Recipe (  //Christian
    //Sidden jeg har haft problemer med at gemme komplekse datastrukture i firebase før har
    // jeg den her gang valgt at gemme al data i simple datatyper og så konvertere dem her i classen.
    // Ulempen ved det er at man ikke kan bruge en ren data class, sidden der er kalkulationer i den, men
    // det ville der være alligevel sidden vi skal finde avgRating.

    val name:String,
    val flavorText:String,
    ingredientsRaw:String,
    val instructions:String,
    ratingsRaw:String,
    val weekNr:Int,
    val peopleAmount:Int
    //form for billedliste
) {
    val ingredients:List<String> = ingredientsRaw
        .replace("[","")
        .replace("]","")
        .split(",")

    val ratings:List<Int> = ratingsRaw
        .replace("[","")
        .replace("]","")
        .split(",")
        .map {char -> char.toInt()}

    val avgRating = ratings.average()
}