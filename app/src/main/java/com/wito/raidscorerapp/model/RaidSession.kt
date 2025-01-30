package com.wito.raidscorerapp.model

data class RaidSession(
    val name : String,
    val weights : MutableMap<String, Double> = mutableMapOf(
        "punctuality" to 2.5,
        "mechanics" to 3.0,
        "consumables" to 1.0,
        "attitude" to 1.5
    ),
    val players : MutableList<Player> = mutableListOf()
) {

    fun updateWeight(criteria: String, newWeight: Double) {
        weights[criteria] = newWeight
    }

    fun calculatePlayerScores(): Map<Player, Double> {
        return  players.associateWith { player ->
            player.calculateFinalScore(weights)
        }
    }

    fun getPlayerTotalScores(): Map<Player, Double> {
        return players.associateWith { player ->
            player.getTotalScore()
        }
    }

    fun getPlayerAverageScores(): Map<Player, Double> {
        return players.associateWith { player ->
            player.getAverageScore()
        }
    }
}