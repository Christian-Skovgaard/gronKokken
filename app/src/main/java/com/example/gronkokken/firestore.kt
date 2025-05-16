package com.example.gronkokken

import android.util.Log
import com.example.gronkokken.dataclasses.Recipe
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class Firestore {
    private val db = Firebase.firestore

    //get all recipes
    suspend fun getAllRecipes ():List<Recipe> {  //Christian
        val collection = db.collection("recipes")

        val returnList:MutableList<Recipe> = mutableListOf()

        collection.get().addOnSuccessListener { recipeList ->
            if (!recipeList.isEmpty) {
                recipeList.forEach{ recipe ->
                    val recipeObj = recipe.toObject<Recipe>()
                    returnList.add(recipeObj)
                }
            }
            else {Log.d("DB-call","recipe list is empty")}
        }.addOnFailureListener{
            Log.d("DB-call","lol, did not work")
        }

        return returnList.toList()  //den returnere en tom liste, også hvis noget går galt, det tror jeg er fedest.
        //Hvis vi har tid kan vi få appen til at skrive en fejlbesked i ui hvis den giver null fx.
    }
}