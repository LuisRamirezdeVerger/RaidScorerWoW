package com.wito.raidscorerapp.model

import androidx.compose.material3.DividerDefaults.color

data class Player (
    val name : String,
    val classWoW : String,
    val specialization : String,
    //Criterios
    var punctuality : Int = 0,
    var mechanics : Int = 0,
    var consumables : Int = 0,
    var attitude : Int = 0
) {
    private val scoreHistory: MutableList<Double> = mutableListOf()

    fun calculateFinalScore(weights: Map<String, Double>): Double {
        val score = (
                punctuality * weights["punctuality"]!! +
                mechanics * weights["mechanics"]!! +
                consumables * weights["consumables"]!! +
                attitude * weights["attitude"]!!
                )
        scoreHistory.add(score)
        return score
    }

    fun getTotalScore(): Double {
        return scoreHistory.sum()
    }

    fun getAverageScore(): Double {
        return if (scoreHistory.isNotEmpty()) scoreHistory.average() else 0.0
    }
}

