package com.wito.raidscorerapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
//import com.wito.raidscorerapp.PlayerItem
import com.wito.raidscorerapp.model.Player
import com.wito.raidscorerapp.utils.classColors

@Composable
fun PlayerListScreen (players: MutableList<Player>, navController: NavController){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column (
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            Text(
                text = "Lista de jugadores",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(players){ player ->
                    val playerColor = classColors[player.classWoW] ?: Color.White

                    //val finalScore = player.calculateFinalScore()

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                        .clickable {
                        navController.navigate("player_detail/${player.name}")
                    },
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        )
                    ){
                        Row (
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Column {
                                Text(text = "Nombre: ${player.name}")
                                Text(text = "Clase: ${player.classWoW}")
                                Text(text = "Especialización: ${player.specialization}")
                                Text(text = "Puntuación final: ${"%.2f".format(player.getTotalScore())}")
                            }
                            Row {
                                Button(
                                    onClick = {
                                        navController.navigate("edit_player/${player.name}")
                                    }
                                ) {
                                    Text("Editar")
                                }
                            }
                        }
                    }
                }
            }
            if (players.isEmpty()){
                Text("No hay jugadores registrados aún")
            }

            Button(
                onClick = {navController.navigateUp()},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Volver")
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

//@Composable
//fun PlayerItem(player: Player, modifier: Modifier = Modifier) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp)
//            .background(MaterialTheme.colorScheme.surface)
//            .padding(16.dp)
//    ) {
//        Text("Nombre: ${player.nombre}", style = MaterialTheme.typography.bodyLarge, color = Color.White)
//        Text("Clase: ${player.clase}", style = MaterialTheme.typography.bodyMedium, color = Color.White)
//        Text("Especialización: ${player.especializacion}", style = MaterialTheme.typography.bodySmall, color = Color.White)
//    }
//}