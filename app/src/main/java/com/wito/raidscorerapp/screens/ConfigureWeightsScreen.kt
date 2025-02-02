package com.wito.raidscorerapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wito.raidscorerapp.model.ScoreWeights

@Composable
fun ConfigureWeightsScreen (
    navController: NavController,
    onWeightsUpdated:(Map<String, Double>) -> Unit
){
    //Weights states
    var punctuality by remember { mutableStateOf(ScoreWeights.punctuality.toString()) }
    var mechanics by remember { mutableStateOf(ScoreWeights.mechanics.toString()) }
    var consumables by remember { mutableStateOf(ScoreWeights.consumables.toString()) }
    var attitude by remember { mutableStateOf(ScoreWeights.attitude.toString()) }
    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //Punctuality field
        OutlinedTextField(
            value = punctuality,
            onValueChange = {punctuality = it},
            label = {Text("Puntualidad")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        //Mechanics field
        OutlinedTextField(
            value = mechanics,
            onValueChange = {mechanics = it},
            label = {Text("Mechs")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        //Consumables field
        OutlinedTextField(
            value = consumables,
            onValueChange = {consumables = it},
            label = {Text("Consumibles")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        //Attittude field
        OutlinedTextField(
            value = attitude,
            onValueChange = {attitude = it},
            label = {Text("Actitud")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        //Button to save weights
        Button(
            onClick = {
                try {
                    //Update weights values
                    ScoreWeights.punctuality = punctuality.toDouble()
                    ScoreWeights.mechanics = mechanics.toDouble()
                    ScoreWeights.consumables = consumables.toDouble()
                    ScoreWeights.attitude = attitude.toDouble()

                    //Notify upodates
                    onWeightsUpdated (
                        mapOf(
                            "punctuality" to ScoreWeights.punctuality,
                            "mechanics" to ScoreWeights.mechanics,
                            "consumables" to ScoreWeights.consumables,
                            "attitude" to ScoreWeights.attitude
                        )
                    )
                    Toast.makeText(context,"Cambios realizados correctamente", Toast.LENGTH_SHORT).show()
                    //Back to previous screen
                    navController.popBackStack()

                } catch (e: NumberFormatException) {
                    Toast.makeText(context,"Error, ingresa valores válidos. ", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Guardar pesos")
        }
    }
}