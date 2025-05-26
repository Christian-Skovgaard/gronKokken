package com.example.gronkokken.components
//filnavnet er med småt, men lad være med at fikse!!!, det dræber github.
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.gronkokken.dataclasses.Recipe
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class Firestore {

    //connection detaljer kan findes i app/google-services.json
    private val db = Firebase.firestore

    //get all recipes
    suspend fun old ():List<Recipe> {  //Christian
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

    suspend fun getAllRecipes ():List<Recipe> { //Christian
        val collection = db.collection("recipes")

        val returnList:MutableList<Recipe> = mutableListOf()

        collection.get().await().forEach {
            returnList.add(it.toObject())
        }

        return returnList.toList()
    }
    suspend fun hentLaunchedEffectData(userId: String): Pair<String, String> { //Sahra
        return try {
            val doc = FirebaseFirestore.getInstance()
                .collection("klimaplan")
                .document(userId)
                .get()
                .await()
            if (doc.exists()) {
                val startpunkt = doc.getString("startpunkt") ?: ""
                val slutpunkt = doc.getString("slutpunkt") ?: ""
                Pair(startpunkt, slutpunkt)
            } else {
                Pair("", "")
            }
        } catch (e: Exception) {
            Log.e("Firestore", "Fejl ved hentning", e)
            Pair("", "")
        }

    }

    fun gemKlimaplanData(userId: String, startpunkt: String, slutpunkt: String) {
        val db = FirebaseFirestore.getInstance()
        val data = hashMapOf(
            "startpunkt" to startpunkt,
            "slutpunkt" to slutpunkt
        )

        db.collection("klimaplan")
            .document(userId)
            .set(data)
            .addOnSuccessListener {
                Log.d("Firestore", "Data gemt for $userId")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Fejl ved gemning", e)
            }
    }

}