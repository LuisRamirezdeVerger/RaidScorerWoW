package com.wito.raidscorerapp.model

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wito.raidscorerapp.screens.AddPlayerScreen
import com.wito.raidscorerapp.screens.ConfigureWeightsScreen
import com.wito.raidscorerapp.screens.EditPlayerScreen
import com.wito.raidscorerapp.screens.PlayerDetailScreen
import com.wito.raidscorerapp.screens.PlayerListScreen
import com.wito.raidscorerapp.screens.RemovePlayerScreen
import com.wito.raidscorerapp.ui.theme.RaidScorerAppTheme
import com.wito.raidscorerapp.Main
import com.wito.raidscorerapp.utils.JsonUtils
import com.wito.raidscorerapp.utils.wowClasses
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.ai.client.generativeai.type.content
import com.wito.raidscorerapp.model.Player
import com.wito.raidscorerapp.screens.ClassSelectionScreen
import com.wito.raidscorerapp.screens.SpecializationSelectionScreen


@Composable
fun MainNavGraph(){
    val navController = rememberNavController()
    val context = LocalContext.current
    val players = remember{ mutableListOf<Player>() }
    var selectedClass by remember { mutableStateOf("") }
    var selectedSpecialization by remember { mutableStateOf("") }

    //Load players from JSON
    LaunchedEffect(Unit) {
        players.addAll(JsonUtils.loadPlayersFromFile(context))
    }

    NavHost(navController = navController, startDestination = "home"){
        composable("home") {
            //Main Screen
            HomeScreen(
                navController = navController,
                players = players
            )
        }

        //Main screen
        //composable("main") {
        //    MainActivity()
        //}

        //Add player screen
        composable("add player") {
            AddPlayerScreen(
                navController = navController,
                selectedClass = selectedClass,
                selectedSpecialization = selectedSpecialization,
                onClassClick = {
                    // navigate to class selection
                    navController.navigate("select_class")
                },
                onSpecializationClick = {
                    // navigate to specialization selection
                    if (selectedClass.isNotEmpty()) {
                        navController.navigate("select_specialization")
                    } else {
                        // Mostrar un mensaje de error si no se ha seleccionado una clase
                        Toast.makeText(context,"Selecciona una clase", Toast.LENGTH_SHORT).show()

                    }
                },
                onAddPlayer = {player ->
                    players.add(player)
                    JsonUtils.savePlayersToFile(context, players)
                    println("Jugador agregado: ${player.name}, ${player.classWoW}, ${player.specialization}")
            })
        }

        composable("select_specialization") {
            SpecializationSelectionScreen(
                navController = navController,
                selectedClass = selectedClass,
                onSpecializationSelected = { specialization ->
                    selectedSpecialization = specialization // Update selected specialization
                }
            )
        }

        composable("select_class") {
            ClassSelectionScreen(
                navController = navController,
                onClassSelected = { className ->
                    selectedClass = className // Update selected class
                }
            )
        }


            //List players screen
        composable("list players") {
            PlayerListScreen(players = players, navController = navController)
        }

        //Delete players screen
        composable("remove players") {
            RemovePlayerScreen(
                players = players,
                navController = navController,
                onRemovePlayer = { player ->
                    JsonUtils.removePlayersAndSave(context, player, players)
                }
            )
        }

        //Player details screen
        composable(
            "player_detail/{playerName}",
            arguments = listOf(navArgument("playerName") {type = NavType.StringType })
        ) { backStackEntry ->  
            val playerName = backStackEntry.arguments?.getString("playerName")
            val player = players.find { it.name == playerName }
            if (player != null){
                PlayerDetailScreen(
                    player = player,
                    onBack = {navController.popBackStack()}
                )
            } else {
                Text("Jugador no encontrado.")
            }
        }

        //Edit player screen
        composable ("edit_player/{playerName}",
            arguments = listOf(navArgument("playerName") { type = NavType.StringType })
        ){ backStackEntry ->
            val playerName = backStackEntry.arguments?.getString("playerName")
            val player = players.find { it.name == playerName }
           if (player != null){
               EditPlayerScreen(
                   player = player,
                   onEditPlayer = { updatedPlayer ->
                       val index = players.indexOf(player)
                       if (index != -1){
                           players[index] = updatedPlayer
                           JsonUtils.savePlayersToFile(context, players)
                       }
                        navController.popBackStack()
                   }
               )
           } else {
               Text("Jugador no encontrado. ")
           }

        }

        //Edit weights screen
        composable("configure_weights") {
            ConfigureWeightsScreen(
                navController = navController,
                onWeightsUpdated = { newWeights ->
                    println("Pesos actualizados")
                }
            )
        }
    }
}

//Background color design
@Composable
fun MysticGradientScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFFD700), Color(0xFF301934)),
                    center = Offset(500f, 800f),
                    radius = 1000f
                )
            )
    ){
        content(
            role = TODO(),
            init = TODO()
        )
    }
}


//Main Screen Design
@Composable
fun HomeScreen(navController: NavController, players: MutableList<Player>){

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    )  {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(text = "Raid Scorer App",
                modifier = Modifier.padding(bottom = 80.dp),
                style = MaterialTheme.typography.headlineMedium
            )

            Button(
                onClick = { navController.navigate("add player")},
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text ("Agregar jugador")
            }

            Button(
                onClick = { navController.navigate("list players") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Listar jugadores")
            }

            Button(onClick = { navController.navigate("remove players") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Text("Eliminar jugador")
            }

            Button(onClick = { navController.navigate("configure_weights")},
                modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) {
                Text("Configurar pesos de criterios")
            }
        }
    }

}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    @SuppressLint("UnrememberedMutableState")
    val examplePlayers = mutableStateListOf(
        Player("Ejemplo1", "Clase1", "Especialización1", "EspecializaciónSecundaria1"),
        Player("Ejemplo2", "Clase2", "Especialización2", "EspecializaciónSecundaria1")
    )
    RaidScorerAppTheme {
        HomeScreen(
            navController = rememberNavController(),
            players = examplePlayers
        )
    }
}