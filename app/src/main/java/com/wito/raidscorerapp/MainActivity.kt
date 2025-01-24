package com.wito.raidscorerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wito.raidscorerapp.screens.AddPlayerScreen
import com.wito.raidscorerapp.ui.theme.RaidScorerAppTheme

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
fun MainMenu(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { /* Navegar a Agregar Jugador */ }) {
                Text (text = "Agregar Jugador")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* Navegar a Ver Puntuaciones */ }) {
                Text (text = "Ver puntuaciones")
            }
        }
    }
}

@Composable
fun MainNavGraph(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home"){
        composable("home") {
            //Main Screen
            HomeScreen (navController = navController)
        }
        composable("add player") {
            AddPlayerScreen(onAddPlayer = {player ->
            })
        }
    }
}

@Composable
fun HomeScreen(navController : NavController){
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = { navController.navigate("add player")}) {
            Text ("Agregar jugador")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    RaidScorerAppTheme {
        HomeScreen(navController = rememberNavController())
    }
}