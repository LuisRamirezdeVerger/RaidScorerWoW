package com.wito.raidscorerapp.model

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.wito.raidscorerapp.utils.JsonUtils


@Composable
fun MainNavGraph(){
    val navController = rememberNavController()
    val context = LocalContext.current
    val players = remember{ mutableListOf<Player>() }

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
                onAddPlayer = {player ->
                    players.add(player)
                    JsonUtils.savePlayersToFile(context, players)
                    println("Jugador agregado: ${player.name}, ${player.classWoW}, ${player.specialization}")
            })
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

@Composable
fun HomeScreen(navController: NavController, players: MutableList<Player>){
        //Main Screen Design
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    )  {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Raid Scorer App",
                modifier = Modifier.padding(bottom = 16.dp),
                style = MaterialTheme.typography.headlineMedium
            )

            Button(
                onClick = { navController.navigate("add player")},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text ("Agregar jugador")
            }

            Button(
                onClick = { navController.navigate("list players") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Listar jugadores")
            }

            Button(onClick = { navController.navigate("remove players") },
                modifier = Modifier.fillMaxWidth()) {
                Text("Eliminar jugador")
            }

            Button(onClick = { navController.navigate("configure_weights")},
                modifier = Modifier.fillMaxWidth().padding(16.dp)
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
        Player("Ejemplo1", "Clase1", "Especialización1"),
        Player("Ejemplo2", "Clase2", "Especialización2")
    )
    RaidScorerAppTheme {
        HomeScreen(
            navController = rememberNavController(),
            players = examplePlayers
        )
    }
}