package com.wito.raidscorerapp.model



object ScoreWeights {
    var punctuality: Double = 2.5
    var mechanics: Double = 3.0
    var consumables: Double = 1.0
    var attitude: Double = 1.5
}

val weights = mapOf(
    "punctuality" to ScoreWeights.punctuality,
    "mechanics" to ScoreWeights.mechanics,
    "consumables" to ScoreWeights.consumables,
    "attitude" to ScoreWeights.attitude
)

//val finalScore = player.calculateFinalScore(weights)