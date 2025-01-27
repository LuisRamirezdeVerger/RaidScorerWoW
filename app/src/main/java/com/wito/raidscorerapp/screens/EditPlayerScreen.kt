package com.wito.raidscorerapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.room.util.copy
import com.wito.raidscorerapp.model.Player
import com.wito.raidscorerapp.utils.classColors
import com.wito.raidscorerapp.utils.ClassDropdown


@Composable
fun EditPlayerScreen(
    player: Player,
    onEditPlayer: (Player) -> Unit
){
    var nombre by remember { mutableStateOf(player.nombre) }
    var clase by remember { mutableStateOf(player.clase) }
    var especializacion by remember { mutableStateOf(player.especializacion) }

    var dropDownExpanded by remember { mutableStateOf(false) }
    var selectedColor by remember { mutableStateOf(classColors[clase]?: Color.White) }

    //Criteria
    var puntualidad by remember { mutableFloatStateOf(player.puntualidad.toFloat()) }
    var mecanicas by remember { mutableFloatStateOf(player.mecanicas.toFloat()) }
    var consumibles by remember { mutableFloatStateOf(player.consumibles.toFloat()) }
    var actitud by remember { mutableFloatStateOf(player.actitud.toFloat()) }

    Column (
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
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


        //Class dropdown
        ClassDropdown(
            selectedClass = clase,
            onClassSelected = { selectedClass ->
                clase = selectedClass
                selectedColor = classColors[selectedClass] ?: Color.White
            },
            modifier = Modifier.fillMaxWidth()
        )



        //Box{
        //    OutlinedTextField(
        //        value = clase,
        //        onValueChange = {}, //There's no manual change
        //        label = { Text("Clase") },
        //        modifier = Modifier
        //            .fillMaxWidth()
        //            .clickable { dropDownExpanded = true },
        //        readOnly = true
        //    )
        //    DropdownMenu(
        //        expanded = dropDownExpanded,
        //        onDismissRequest = { dropDownExpanded = false }
        //    ) {
        //        classColors.keys.forEach { availableClass ->
        //            DropdownMenuItem(
        //                text = { Text(availableClass) },
        //                onClick = {
        //                    clase = availableClass
        //                    selectedColor = classColors[availableClass] ?: Color.White
        //                    dropDownExpanded = false
        //                }
        //            )
        //        }
        //    }
        //}



        OutlinedTextField(
            value = especializacion,
            onValueChange = {especializacion = it},
            label = { Text("Especialización") },
            modifier = Modifier.fillMaxWidth()
        )

        //Slider criteria selection
        CriterioSlider(
            label = "Puntualidad",
            value = puntualidad,
            onValueChange = {puntualidad = it}
        )

        CriterioSlider(
            label = "Mecanicas",
            value = mecanicas,
            onValueChange = {mecanicas = it}
        )

        CriterioSlider(
            label = "Consumibles",
            value = consumibles,
            onValueChange = {consumibles = it}
        )

        CriterioSlider(
            label = "Actitud",
            value = actitud,
            onValueChange = {actitud = it}
        )

        Button(
            onClick = {
                val updatedPlayer = player.copy(
                    nombre = nombre,
                    clase = clase,
                    especializacion = especializacion,
                    puntualidad = puntualidad.toInt(),
                    mecanicas = mecanicas.toInt(),
                    consumibles = consumibles.toInt(),
                    actitud = actitud.toInt()
                    )
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

@Composable
fun CriterioSlider(
    label : String,
    value : Float,
    onValueChange: (Float) -> Unit
){
    Column (modifier = Modifier.fillMaxWidth()){
        Text(
            text = "$label: ${value.toInt()}",
            style = MaterialTheme.typography.bodyLarge
        )
        Slider (
            value = value,
            onValueChange = onValueChange,
            valueRange = 1f..3f,
            steps = 1,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}