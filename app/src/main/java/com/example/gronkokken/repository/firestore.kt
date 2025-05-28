package com.example.gronkokken.repository
//filnavnet er med småt, men lad være med at fikse!!!, det gør github skræmt fra vid og sans :D.
import android.util.Log
import com.example.gronkokken.models.Recipe
import com.example.gronkokken.models.SeasonalIngredient
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import com.google.firebase.storage.FirebaseStorage
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
            recipe.initLogic()
            returnList.add(recipe)
        }

        Log.d("DB-Call","amount of collected recipes: " + returnList.size.toString())
        return returnList.toList()
    }

    suspend fun getRecipeByName (name:String): Recipe { //Christian
        val collection = db.collection("recipes")

        var recipe: Recipe = Recipe()

        collection.whereEqualTo("name",name).get().await().forEach {    //der kan være problemer hvis der kommer mere end 1 resultat, men det burde der ikke:D
            recipe = it.toObject<Recipe>()
            recipe.id = it.id
            recipe.initLogic()
        }

        return recipe
    }

    suspend fun getRecipeById (id:String): Recipe { //Christian
        val document = db.collection("recipes").document(id)

        var recipe: Recipe = Recipe()

        val item = document.get().await()

        recipe = item.toObject<Recipe>()!!  //vi er sikre på at der ikke er null, siden alle id som bruges i appen er taget fra databasen
        recipe.id = item.id
        recipe.initLogic()

        return recipe
    }

    suspend fun getCurrentRecipeId ():String {  //Christian
        val currentDate = LocalDate.now()
        val daysToNextSunday = java.time.DayOfWeek.SUNDAY.value - currentDate.dayOfWeek.value //søndag er altid 7, har bare inkluderet DayOfWeek så det var nemere lige at gennemskue hvad der sker
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
    suspend fun hentLaunchedEffectData(documentId: String): Pair<String, String> {
        return try {
            val doc = FirebaseFirestore.getInstance()
                .collection("klimaplan")
                .document(documentId)
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

    // 🔵 Funktion til at gemme data
    fun gemKlimaplanData(documentId: String, startpunkt: String, slutpunkt: String) {
        val db = FirebaseFirestore.getInstance()
        val data = hashMapOf(
            "startpunkt" to startpunkt,
            "slutpunkt" to slutpunkt
        )

        db.collection("klimaplan")
            .document(documentId)
            .set(data)
            .addOnSuccessListener {
                Log.d("Firestore", "Data gemt i dokumentet: $documentId")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Fejl ved gemning", e)
            }
    }


    //used for uploading recipes
    suspend fun uploadRecipe (
        name:String = "",
//        flavorText:String = "",
//        ingredientsRaw:List<Map<String,String>> = listOf(), //Vi gemmer i Map fordi jeg har haft mange problemer med at gemme i custom classes i firestore.
//        instructions:String = "",
//        ratings:List<Int> = listOf(),
//        endDateRaw:String = "2025-05-28",
//        peopleAmount:Int = 1
    ) {
        val collection = db.collection("recipes")

        Log.d("lookhere",collection.add(name).await().id)
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