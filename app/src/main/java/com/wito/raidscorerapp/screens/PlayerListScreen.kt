package com.wito.raidscorerapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wito.raidscorerapp.PlayerItem
import com.wito.raidscorerapp.model.Player

@Composable
fun PlayerListScreen (players: List<Player>, navController: NavController){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column (
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ){
            Text(
                text = "Lista de jugadores",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn (
                modifier = Modifier.fillMaxSize()
            ){
                items(players){ player ->
                    PlayerItem(player)
                }
            }

            Button(
                onClick = {navController.popBackStack()},
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text("Volver")
            }
        }
    }
}