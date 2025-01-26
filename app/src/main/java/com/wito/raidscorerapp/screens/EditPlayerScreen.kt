package com.wito.raidscorerapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wito.raidscorerapp.model.Player


@Composable
fun EditPlayerScreen(
    player: Player,
    onEditPlayer: (Player) -> Unit
){
    var nombre by remember { mutableStateOf(player.nombre) }
    var clase by remember { mutableStateOf(player.clase) }
    var especializacion by remember { mutableStateOf(player.especializacion) }

    Column (
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Editar jugador",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = {nombre = it},
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = clase,
            onValueChange = {clase = it},
            label = { Text("Clase") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = especializacion,
            onValueChange = {especializacion = it},
            label = { Text("Especialización") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val updatedPlayer = Player(nombre, clase, especializacion)
                onEditPlayer(updatedPlayer)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar cambios")
        }

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Cancelar")
        }
    }
}