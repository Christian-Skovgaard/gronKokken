package com.example.gronkokken.components
//filnavnet er med småt, men lad være med at fikse!!!, det dræber github.
import android.util.Log
import com.example.gronkokken.models.Recipe
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

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

            //testing calls
            Log.d("lookmom", it.data["ingredientsRaw"].toString())
            var map: Map<String,String> = mapOf()
        }

        return returnList.toList()
    }

    suspend fun getRecipeByName (name:String): Recipe { //Christian
        val collection = db.collection("recipes")

        var recipe: Recipe = Recipe()

        collection.whereEqualTo("name",name).get().await().forEach {    //der kan være problemer hvis der kommer mere end 1 resultat, men det burde der ikke:D
            recipe = it.toObject<Recipe>()
            recipe.id = it.id
        }

        return recipe
    }

    suspend fun getRecipeById (id:String): Recipe { //Christian
        val document = db.collection("recipes").document(id)

        var recipe: Recipe = Recipe()

        val item = document.get().await()

        recipe = item.toObject<Recipe>()!!  //vi er sikre på at der ikke er null, sidden alle id som bruge i appen er taget fra databasen
        recipe.id = item.id

        //Log.d("lookmom",item.data.toString())
        //Log.d("lookmom",recipe.toString())
        return recipe
    }
}