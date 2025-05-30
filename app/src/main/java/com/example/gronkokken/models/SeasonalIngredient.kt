package com.example.gronkokken.models

//Lukas
data class SeasonalIngredient(
    var id: String = "",
    var name: String = "",
    var startMonth: Int = 1,
    var endMonth: Int = 12,
    var description: String = "",
    var isFruit: Boolean = true
) {

    fun isInSeason(currentMonth: Int): Boolean {
        return if (startMonth <= endMonth) {
            // Normal period
            currentMonth in startMonth..endMonth
        } else {
            // overlapping year change
            currentMonth >= startMonth || currentMonth <= endMonth
        }
    }




    override fun toString(): String {
        return "$name, $startMonth, $endMonth, $description, $id, $isFruit"
    }
}