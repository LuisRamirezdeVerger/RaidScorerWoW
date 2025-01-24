package com.wito.raidscorerapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.wito.raidscorerapp.model.Player
import com.wito.raidscorerapp.screens.AddPlayerScreen

@Composable
fun  AddPlayerScreen(
    navController: NavController,
    onAddPlayer: (Player) -> Unit)  {
    var nombre by remember { mutableStateOf("") }
    var clase by remember { mutableStateOf("") }
    var especializacion by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Agregar Jugador", style = MaterialTheme.typography.headlineMedium)

        Spacer (modifier = Modifier.height(16.dp))

            //Name Field
        TextField(
            value = nombre,
            onValueChange = {nombre = it},
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer (modifier = Modifier.height(8.dp))

            //Class field
        TextField(
            value = clase,
            onValueChange = {clase = it},
            label = { Text("Clase") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer (modifier = Modifier.height(8.dp))

            //Spec field
        TextField(
            value = especializacion,
            onValueChange = {especializacion = it},
            label = { Text("Especialización") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer (modifier = Modifier.height(16.dp))

            //Add player button
        Button(
            onClick = {
                if (nombre.isNotBlank() && clase.isNotBlank() && especializacion.isNotBlank()){
                    onAddPlayer (Player(nombre, clase, especializacion))
                    Toast.makeText(context,"Jugador $nombre agregado correctamente", Toast.LENGTH_SHORT).show()
                    // Navegamos de regreso a la pantalla principal
                    navController.popBackStack()
                } else {
                    Toast.makeText(context,"Faltan campos por completar", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar Jugador")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddPlayerScreen() {
    AddPlayerScreen(
        onAddPlayer = {},
        navController = rememberNavController()
    )
}