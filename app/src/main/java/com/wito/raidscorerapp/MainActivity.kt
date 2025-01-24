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
fun MainNavGraph(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home"){
        composable("home") {
            //Main Screen
            HomeScreen (navController = navController)
        }
        composable("add player") {
            AddPlayerScreen(
                navController = navController,
                onAddPlayer = {player ->
                    println("Player agregado: ${player.nombre}, ${player.clase}, ${player.especializacion}")
            })
        }
    }
}

@Composable
fun HomeScreen(navController : NavController){
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
            Text(text = "Raid Scorer App", modifier = Modifier.padding(bottom = 16.dp))
            Button(onClick = { navController.navigate("add player")}) {
                Text ("Agregar jugador")
            }
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