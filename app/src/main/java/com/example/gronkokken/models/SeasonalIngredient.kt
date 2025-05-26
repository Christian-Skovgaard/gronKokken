package com.example.gronkokken.models

data class SeasonalIngredient(
    var id: String = "",
    val name: String = "",
    val startMonth: Int = 1,
    val endMonth: Int = 12,
    val description: String = ""
) {





    override fun toString(): String {
        return "yo yo yo its $name and im $description"
    }
}