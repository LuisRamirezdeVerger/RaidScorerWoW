package com.wito.raidscorerapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wito.raidscorerapp.model.Player
import com.wito.raidscorerapp.utils.ClassDropdown


@Composable
fun PlayerDetailScreen(
    player : Player,
    onBack: () -> Unit
){
    Column (
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Text(
            text = "Detalles de ${player.name}",
            style = MaterialTheme.typography.headlineMedium
        )
        //Individual criteria
        Text(
            text = "Puntualidad: ${player.punctuality}",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Mecánicas: ${player.mechanics}",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Consumibles: ${player.consumables}",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Actitud: ${player.attitude}",
            style = MaterialTheme.typography.headlineMedium
        )

        //Back Button
        Button(
            onClick = {onBack()},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}
