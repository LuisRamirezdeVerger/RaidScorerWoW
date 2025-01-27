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
        "puntualidad" to 2.5,
        "mecanicas" to 3.0,
        "consumibles" to 2.0,
        "actitud" to 2.0
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

