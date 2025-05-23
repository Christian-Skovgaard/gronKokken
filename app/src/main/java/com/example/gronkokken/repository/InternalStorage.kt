package com.example.gronkokken.components

import android.content.Context
import android.util.Log

class InternalStorage () {  //Christian
    //Context skal sendes fra MainActivity
    //på telefon kan filerne findes under /data/data/com.example.gronkokken/files/

    fun updateRecipeRating (context: Context) {
        val fileName = "ratings.txt"
        val content = "hello there"

        fun write (context: Context, fileName:String, content:String) {
            context.openFileOutput(fileName,Context.MODE_PRIVATE).use{
                it.write(content.toByteArray())
            }
        }
    }

    fun getRecipeRatingMap (): Map<String,Int>? {
        return null
    }

    fun write (context: Context, fileName:String, content:String) {
        context.openFileOutput(fileName,Context.MODE_PRIVATE).use{
            it.write(content.toByteArray())
        }
    }




    fun read (context: Context, fileName:String) {
        val output = context.openFileInput(fileName).bufferedReader().useLines { lines ->
            lines.joinToString("\n")
        }
        Log.d("lookmom",output.toString())
    }
}