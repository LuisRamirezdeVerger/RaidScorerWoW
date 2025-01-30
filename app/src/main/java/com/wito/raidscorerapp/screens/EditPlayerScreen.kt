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
    var name by remember { mutableStateOf(player.name) }
    var classWoW by remember { mutableStateOf(player.classWoW) }
    var specialization by remember { mutableStateOf(player.specialization) }

    var dropDownExpanded by remember { mutableStateOf(false) }
    var selectedColor by remember { mutableStateOf(classColors[classWoW]?: Color.White) }

    //Criteria
    var punctuality by remember { mutableFloatStateOf(player.punctuality.toFloat()) }
    var mechanics by remember { mutableFloatStateOf(player.mechanics.toFloat()) }
    var consumables by remember { mutableFloatStateOf(player.consumables.toFloat()) }
    var attitude by remember { mutableFloatStateOf(player.attitude.toFloat()) }

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
            value = name,
            onValueChange = {name = it},
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )


        //Class dropdown
        ClassDropdown(
            selectedClass = classWoW,
            onClassSelected = { selectedClass ->
                classWoW = selectedClass
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
            value = specialization,
            onValueChange = {specialization = it},
            label = { Text("Especialización") },
            modifier = Modifier.fillMaxWidth()
        )

        //Slider criteria selection
        CriterioSlider(
            label = "Puntualidad",
            value = punctuality,
            onValueChange = {punctuality = it}
        )

        CriterioSlider(
            label = "Mecanicas",
            value = mechanics,
            onValueChange = {mechanics = it}
        )

        CriterioSlider(
            label = "Consumibles",
            value = consumables,
            onValueChange = {consumables = it}
        )

        CriterioSlider(
            label = "Actitud",
            value = attitude,
            onValueChange = {attitude = it}
        )

        Button(
            onClick = {
                val updatedPlayer = player.copy(
                    name = name,
                    classWoW = classWoW,
                    specialization = specialization,
                    punctuality = punctuality.toInt(),
                    mechanics = mechanics.toInt(),
                    consumables = consumables.toInt(),
                    attitude = attitude.toInt()
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