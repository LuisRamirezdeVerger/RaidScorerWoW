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
    //private val pesos = mapOf(
    //    "puntualidad" to 2.5,
    //    "mecanicas" to 3.0,
    //    "consumibles" to 2.0,
    //    "actitud" to 2.0
    //)

    fun calculatFinalScore(): Double {
        return (
                punctuality * ScoreWeights.punctuality +
                        mechanics * ScoreWeights.mechanics +
                        consumables * ScoreWeights.consumables +
                        attitude * ScoreWeights.attitude
                )
    }
}

