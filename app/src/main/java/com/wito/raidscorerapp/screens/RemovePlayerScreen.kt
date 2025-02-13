package com.wito.raidscorerapp.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wito.raidscorerapp.model.Player


@Composable
fun RemovePlayerScreen(
    initialPlayers: MutableList<Player>,
    navController: NavController,
    onRemovePlayer: (Player) -> Unit
) {
    val context = LocalContext.current
    val players = remember { mutableStateListOf<Player>().apply { addAll(initialPlayers) } }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ){
            Text(
                text = "Eliminar Jugador",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn (
                modifier = Modifier.fillMaxSize()
            ){
                items(players){player ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        )
                    ) {
                        Row (
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Column {
                                Text(text = "Nombre: ${player.name}")
                                Text(text = "Nombre: ${player.classWoW}")
                                Text(text = "Nombre: ${player.specialization}")
                            }
                            Button(
                                onClick = {
                                    try {
                                        players.remove(player)
                                        onRemovePlayer(player)
                                        Toast.makeText(context, "Jugador ${player.name} eliminado", Toast.LENGTH_LONG).show()
                                        //run { navController.popBackStack() }
                                    } catch (e: Exception) {
                                        Log.e("RemovePlayer", "Error al eliminar el jugador, ${e.message}")
                                    }

                                }
                            ) {
                                Text(text = "Eliminar")
                            }
                        }
                    }
                }
            }
            Button(
                onClick = { navController.popBackStack() }, // Volver al menú principal
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver")
            }
        }
    }
}
