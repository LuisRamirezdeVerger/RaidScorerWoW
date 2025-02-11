package com.wito.raidscorerapp.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
//import com.wito.raidscorerapp.R


val classColors = mapOf(
    "Guerrero" to Color(0xFFC79C6E),
    "Mago" to Color(0xFF69CCF0),
    "Druida" to Color(0xFFFF7D0A),
    "Cazador" to Color(0xFFABD473),
    "Pícaro" to Color(0xFFFFF569),
    "Chamán" to Color(0xFF0070DE),
    "Brujo" to Color(0xFF9482C9),
    "Sacerdote" to Color(0xFFFFFFFF),
    "Paladín" to Color(0xFFF58CBA),
    "Caballero de la muerte" to Color(0xFFC41F3B),
    "Monje" to Color(0xFF00FF96),
    "Cazador de demonios" to Color(0xFFA330C9),
    "Evocador" to Color(0xFF33937F)
)

val wowClasses = listOf(
    "Guerrero",
    "Paladín",
    "Cazador",
    "Pícaro",
    "Sacerdote",
    "Caballero de la Muerte",
    "Chamán",
    "Mago",
    "Brujo",
    "Monje",
    "Druida",
    "Cazador de demonios",
    "Evocador"
)

val classSpecializations = mapOf(
    "Guerrero" to listOf("Armas", "Furia", "Protección"),
    "Mago" to listOf("Fuego", "Escarcha", "Arcano"),
    "Druida" to listOf("Equilibrio", "Feral", "Guardián", "Restauración"),
    "Cazador" to listOf("Bestias", "Puntería", "Supervivencia"),
    "Pícaro" to listOf("Asesinato", "Combate", "Sutileza"),
    "Chamán" to listOf("Elemental", "Mejora", "Restauración"),
    "Brujo" to listOf("Aflicción", "Demonología", "Destrucción"),
    "Sacerdote" to listOf("Disciplina", "Sagrado", "Sombra"),
    "Paladín" to listOf("Sagrado", "Protección", "Reprensión"),
    "Caballero de la muerte" to listOf("Sangre", "Escarcha", "Profano"),
    "Monje" to listOf("Maestro cervecero", "Tejedor de niebla", "Viajero del viento"),
    "Cazador de demonios" to listOf("Devastación", "Venganza"),
    "Evocador" to listOf("Devastación", "Preservación", "Aumento")
)

val specializationRoles = mapOf(
    // Guerrero
    "Armas" to "DPS",
    "Furia" to "DPS",
    "Protección" to "Tank",

    // Mago
    "Fuego" to "DPS",
    "Escarcha" to "DPS",
    "Arcano" to "DPS",

    // Druida
    "Equilibrio" to "DPS",
    "Feral" to "DPS",
    "Guardián" to "Tank",
    "Restauración" to "Healer",

    // Cazador
    "Bestias" to "DPS",
    "Puntería" to "DPS",
    "Supervivencia" to "DPS",

    // Pícaro
    "Asesinato" to "DPS",
    "Combate" to "DPS",
    "Sutileza" to "DPS",

    // Chamán
    "Elemental" to "DPS",
    "Mejora" to "DPS",
    "Restauración" to "Healer",

    // Brujo
    "Aflicción" to "DPS",
    "Demonología" to "DPS",
    "Destrucción" to "DPS",

    // Sacerdote
    "Disciplina" to "Healer",
    "Sagrado" to "Healer",
    "Sombra" to "DPS",

    // Paladín
    "Sagrado" to "Healer",
    "Protección" to "Tank",
    "Reprensión" to "DPS",

    // Caballero de la muerte
    "Sangre" to "Tank",
    "Escarcha" to "DPS",
    "Profano" to "DPS",

    // Monje
    "Maestro cervecero" to "Tank",
    "Tejido de niebla" to "Healer",
    "Viajero del viento" to "DPS",

    // Cazador de demonios
    "Devastación" to "DPS",
    "Venganza" to "Tank",

    // Evocador
    "Devastación" to "DPS",
    "Preservación" to "Healer",
    "Aumento" to "Supp"
)

//val roleIcons = mapOf(
//    "Tank" to painterResource(id = R.drawable.tank_icon),
//    "DPS" to painterResource(id = R.drawable.dps_icon),
//    "Healer" to painterResource(id = R.drawable.healer_icon),
//    "Supp" to painterResource(id = R.drawable.augment_icon)
//)