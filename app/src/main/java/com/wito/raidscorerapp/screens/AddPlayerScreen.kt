package com.wito.raidscorerapp.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.wito.raidscorerapp.model.Player
import com.wito.raidscorerapp.screens.AddPlayerScreen

@Composable
fun AddPlayerScreen(
    navController: NavController,
    selectedClass: String,
    onClassClick: () -> Unit,
    onAddPlayer: (Player) -> Unit
)  {
    var name by remember { mutableStateOf("") }
    var classWoW by remember { mutableStateOf("") }
    var specialization by remember { mutableStateOf("") }
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
            value = name,
            onValueChange = {name = it},
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer (modifier = Modifier.height(8.dp))

            //Class field
        Button(
            onClick = onClassClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (selectedClass.isEmpty()) "Seleccionar Clase" else "Clase: $selectedClass")
        }

        Spacer (modifier = Modifier.height(8.dp))

            //Spec field
        TextField(
            value = specialization,
            onValueChange = {specialization = it},
            label = { Text("Especialización") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer (modifier = Modifier.height(16.dp))

            //Add player button
        Button(
            onClick = {
                if (name.isNotBlank() && classWoW.isNotBlank() && specialization.isNotBlank()){
                    onAddPlayer (Player(name, classWoW, specialization))
                    Toast.makeText(context,"Jugador $name agregado correctamente", Toast.LENGTH_SHORT).show()
                    // Navigate back to Main Screen
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
    BackButton(navController)
}

@Composable
fun BackButton(navController: NavController){
    Button(
        onClick = {navController.popBackStack()},
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text("Volver")
    }
}


//@Composable
//fun PlayerItem(player : Player, navController: NavController){
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
//
//}

@Preview(showBackground = true)
@Composable
fun PreviewAddPlayerScreen() {
    AddPlayerScreen(
        onAddPlayer = {},
        navController = rememberNavController(),
        selectedClass = TODO(),
        onClassClick = TODO()
    )
}