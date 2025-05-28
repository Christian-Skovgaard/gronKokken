package com.example.gronkokken.repository
//filnavnet er med småt, men lad være med at fikse!!!, det dræber github.
import android.util.Log
import com.example.gronkokken.models.Recipe
import com.example.gronkokken.models.SeasonalIngredient
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import com.google.firebase.storage.FirebaseStorage
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
        recipe.updateIngredients()

        Log.d("lookmom","item from database = " + item.data.toString())
        Log.d("lookmom","item returned from function" + recipe.ingredientsRaw.toString())
        return recipe
    }

    //Lukas
    suspend fun getSeasonalIngredients ():List<SeasonalIngredient> {
        val collection = db.collection("ingredients")

        val returnList:MutableList<SeasonalIngredient> = mutableListOf()

        collection.get().await().forEach {
            val name = it.getString("name") ?: ""
            val description = it.getString("description") ?: ""
            val startMonth = it.getLong("startMonth")?.toInt() ?: 1
            val endMonth = it.getLong("endMonth")?.toInt() ?: 12
            val isFruit = it.getBoolean("isFruit") ?: true

            val ingredient = SeasonalIngredient(
                id = it.id,
                name = name,
                startMonth = startMonth,
                endMonth = endMonth,
                description = description,
                isFruit = isFruit
            )

            returnList.add(ingredient)
        }

        return returnList

    }

    fun getImageUrl(
        imagePath: String,
        onResult: (String?) -> Unit
    ) {
        val storageRef = FirebaseStorage.getInstance().reference.child(imagePath)
        storageRef.downloadUrl
            .addOnSuccessListener { url ->
                onResult(url.toString())
            }
            .addOnFailureListener {
                onResult(null)
            }
    }
}