package com.wito.raidscorerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wito.raidscorerapp.model.Player
import com.wito.raidscorerapp.screens.AddPlayerScreen
import com.wito.raidscorerapp.screens.PlayerListScreen
import com.wito.raidscorerapp.ui.theme.RaidScorerAppTheme
import com.wito.raidscorerapp.utils.JsonUtils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RaidScorerAppTheme {
                MainNavGraph()
            }
        }
    }
}

@Composable
fun MainNavGraph(){
    val navController = rememberNavController()
    val context = LocalContext.current
    val players = remember { mutableListOf<Player>() }

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

        composable("main") {
            MainActivity()
        }

        composable("add player") {
            AddPlayerScreen(
                navController = navController,
                onAddPlayer = {player ->
                    players.add(player)
                    JsonUtils.savePlayersToFile(context, players)
                    println("Player agregado: ${player.nombre}, ${player.clase}, ${player.especializacion}")
            })
        }
        composable("list players") {
            PlayerListScreen(players = players, navController = navController)
        }
    }
}

@Composable
fun HomeScreen(navController : NavController, players: List<Player>){
        //Main Screen Design
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    )  {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize().padding(16.dp)
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
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    RaidScorerAppTheme {
        HomeScreen(
            navController = rememberNavController(),
            players = TODO()
        )
    }
}