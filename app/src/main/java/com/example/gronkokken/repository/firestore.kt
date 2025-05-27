package com.example.gronkokken.repository
//filnavnet er med småt, men lad være med at fikse!!!, det dræber github.
import android.util.Log
import com.example.gronkokken.models.Recipe
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import java.time.LocalDate

class Firestore {

    //connection detaljer kan findes i app/google-services.json
    private val db = Firebase.firestore

    suspend fun getAllRecipes ():List<Recipe> { //Christian
        val collection = db.collection("recipes")

        val returnList:MutableList<Recipe> = mutableListOf()

        collection.get().await().forEach {
            val recipe: Recipe = it.toObject()
            recipe.id = it.id
            returnList.add(recipe)
        }

        return returnList.toList()
    }

    suspend fun getRecipeByName (name:String): Recipe { //Christian
        val collection = db.collection("recipes")

        var recipe: Recipe = Recipe()

        collection.whereEqualTo("name",name).get().await().forEach {    //der kan være problemer hvis der kommer mere end 1 resultat, men det burde der ikke:D
            recipe = it.toObject<Recipe>()
            recipe.id = it.id
            recipe.createIngredientsFromRaw()
        }

        return recipe
    }

    suspend fun getRecipeById (id:String): Recipe { //Christian
        val document = db.collection("recipes").document(id)

        var recipe: Recipe = Recipe()

        val item = document.get().await()

        recipe = item.toObject<Recipe>()!!  //vi er sikre på at der ikke er null, siden alle id som bruges i appen er taget fra databasen
        recipe.id = item.id
        recipe.createIngredientsFromRaw()

        return recipe
    }

    suspend fun getCurrentRecipeId ():String {  //Christian
        val currentDate = LocalDate.now()
        val daysToNextSunday = java.time.DayOfWeek.SUNDAY.value - currentDate.dayOfWeek.value //søndag er altid 7, har bare inkluderet DayOfWeek så det var nemere lige at gennemskue hvad der skete
        val comingSunday = currentDate.plusDays(daysToNextSunday.toLong())

        val collection = db.collection("recipes")
        val responseList = collection.whereEqualTo("endDateRaw",comingSunday.toString()).get().await()

        if (responseList.size() > 1) {
            Log.d("DB-Call","missing or overlapping date in DB")
            return responseList.toList()[0].id
        }
        else if (responseList.size() < 1) {
            Log.d("DB-Call","no opskrift i dag<3, fejl")
            return "problem"
        }
        else {
            return responseList.toList()[0].id
        }
    }
}