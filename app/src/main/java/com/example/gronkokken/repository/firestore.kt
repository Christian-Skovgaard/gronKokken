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
            Log.d("DB-Call","no recipe today<3, sorry")
            return "problem"
        }
        else {
            Log.d("DB-Call","current recipe id is " + responseList.toList()[0].id)
            return responseList.toList()[0].id
        }
    }

    suspend fun hentLaunchedEffectData(documentId: String): Pair<String, String> {  //Sahra
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
    fun gemKlimaplanData(documentId: String, startpunkt: String, slutpunkt: String) { //Sahra
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

    //chatgpt
    fun getImageUrl(
        imagePath: String,
        onResult: (String?) -> Unit
    ) {
        Log.d("DB-Call","looking for image")
        val storageRef = FirebaseStorage.getInstance().reference.child(imagePath)
        storageRef.downloadUrl
            .addOnSuccessListener { url ->
                onResult(url.toString())
                Log.d("DB-Call","image is here")
            }
            .addOnFailureListener {
                Log.e("FirebaseImage", "Kunne ikke hente billede: $imagePath", it)
                onResult(null)
                Log.d("DB-Call","no image lol " + it.toString())
            }
    }


    //funktionener her var brugt til at uploade opskrifter til DB, da
    // det tager lang tid at gøre gennem firestore control-panel,
    // den er efterladt i koden for at dokumentere hvordan det blev gjordt:D
    fun uploadRecipe (  //Christian
//        name: String,
//        flavorText:String = "",
        ingredientsRawString: String,
//        instructions:String,
//        endDateRaw:String,
//        peopleAmount:Int = 1,
//        imagePath:String,
    ) {

        //ingredient handling
        val ingredientsRaw: MutableList<Map<String,String>> = mutableListOf()

        val listOfIngredientString = ingredientsRawString.lines()   //virker ligesom .split("\n")
        listOfIngredientString.forEach {
            if (!it.isBlank()) {
                val split = it.trim().split(" ")
                if (split[0].toIntOrNull() == null && split[0].toDoubleOrNull() == null) {  //Hvis stringen ikke passer i typen bliver det til null
                    ingredientsRaw.add(
                        mapOf(
                            "amount" to "",
                            "amountUnit" to "",
                            "name" to split.joinToString(" ")
                        )
                    )
                } else {
                    if (split.size >= 3) {
                        ingredientsRaw.add(
                            mapOf(
                                "amount" to split[0],
                                "amountUnit" to split[1],
                                "name" to split.subList(2, split.size).joinToString(" ") //.sublist() laver en liste fra et index til et andet som vi så sætter sammen til string
                            )
                        )
                    } else if (split.size == 2) {
                        ingredientsRaw.add(
                            mapOf(
                                "amount" to split[0],
                                "amountUnit" to "",
                                "name" to split.subList(1, split.size).joinToString(" ")
                            )
                        )
                    }
                }
                Log.d("data_upload", split.toString())
            }
        }
        Log.d("data_upload",ingredientsRaw.toString())

        //rating handling
        //Sidden vi ikke har nået at lave et rigtigt rating system har jeg her for sjov lavet en tilfældigt generet liste
        val ratings = List((1..100).random()) { (1..5).random() }

//        db.collection("recipes").add(hashMapOf(
//            "name" to name,
//            "flavorText" to flavorText,
//            "ingredientsRaw" to ingredientsRaw,
//            "instructions" to instructions,
//            "ratings" to ratings,
//            "endDateRaw" to endDateRaw,
//            "peopleAmount" to peopleAmount,
//            "imagePath" to imagePath
//        )).addOnSuccessListener {
//            Log.d("data_upload",it.toString())
//        }.addOnFailureListener {
//            Log.d("data_upload",it.toString())
//        }
    }


    suspend fun uploadByName (    //Christian
        recipeId: String,
        ingredientsRawString: String
    ) {
        val ingredientsRaw: MutableList<Map<String, String>> = mutableListOf()

        val listOfIngredientString = ingredientsRawString.lines()
        listOfIngredientString.forEach {
            var amount:String = ""
            var amountUnit:String = ""
            var name:String = ""
            if (!it.isBlank()) {
                val list = it.split(" ")
                if (!list[0][0].isDigit()) {
                    name = list.joinToString(" ")
                } else {
                    val regexForDigits = "\\d+".toRegex()
                    amount = regexForDigits.find(list[0]).toString()
                    amountUnit = list[0].replace(amount,"")
                    name = list.subList(1,list.size).joinToString(" ")
                }
            }
            ingredientsRaw.add(mapOf(
                "amount" to amount,
                "amountUnit" to amountUnit,
                "name" to name
            ))
        }

        val ratings = List((1..100).random()) { (1..5).random() }

        val updateMap = hashMapOf(
            "ingredientsRaw" to ingredientsRaw,
            "ratings" to ratings,
            "peopleAmount" to 1
        )

        db.collection("recipes").document(recipeId).update(updateMap)
            .addOnSuccessListener { Log.d("upload","worked fine:D " + it.toString()) }
            .addOnFailureListener { Log.d("upload","worked fine:D " + it.toString()) }
    }
}