package com.wito.raidscorerapp.model

data class Player (
    val nombre : String,
    val clase : String,
    val especializacion : String,
    //Criterios
    var puntualidad : Int = 0,
    var mecanicas : Int = 0,
    var consumibles : Int = 0,
    var actitud : Int = 0
) {
    private val pesos = mapOf(
        "puntualidad" to 0.25,
        "mecanicas" to 0.3,
        "consumibles" to 0.2,
        "actitud" to 0.2
    )

    fun calcularPuntuacionFinal(): Double {
        return (
                puntualidad * pesos["puntualidad"]!! +
                mecanicas * pesos["mecanicas"]!! +
                consumibles * pesos["consumibles"]!! +
                actitud * pesos["actitud"]!!
                )
    }
}

