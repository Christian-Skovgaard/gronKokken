package com.example.gronkokken.models

data class SeasonalIngredient(
    var id: String = "",
    val name: String = "",
    val startMonth: Int = 1,
    val endMonth: Int = 12,
    val description: String = ""
) {

    fun isInSeason(currentMonth: Int): Boolean {
        return if (startMonth <= endMonth) {
            // Normal periode
            currentMonth in startMonth..endMonth
        } else {
            // Overlapper årsskifte
            currentMonth >= startMonth || currentMonth <= endMonth
        }
    }




    override fun toString(): String {
        return "$name, $startMonth, $endMonth, $description, $id"
    }
}