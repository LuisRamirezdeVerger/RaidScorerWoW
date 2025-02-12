package com.wito.raidscorerapp.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wito.raidscorerapp.utils.classColors
import com.wito.raidscorerapp.utils.wowClasses
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.clip
import com.wito.raidscorerapp.model.Player
import com.wito.raidscorerapp.utils.classSpecializations
import com.wito.raidscorerapp.utils.specializationColors
import com.wito.raidscorerapp.utils.specializationRoles


@Composable
fun SpecializationSelectionScreen (
    navController: NavController,
    selectedClass: String,
    onSpecializationSelected: (String) -> Unit
){
    //Get specs from class selected
    val specializations = classSpecializations[selectedClass] ?: emptyList()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Selecciona una especialización",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        specializations.forEach{ specialization ->
            val role = specializationRoles[specialization] ?: "DPS"
            val specColor = specializationColors[role] ?: Color.Gray

            Button(
                onClick = {
                    onSpecializationSelected(specialization) //Callback to selected spec
                    navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(4.dp, SolidColor(Color.Black), RoundedCornerShape(32.dp)).clip(RoundedCornerShape(32.dp)),
                shape = RoundedCornerShape(32.dp),
                colors = ButtonDefaults.buttonColors(containerColor = specColor)
                ) {
                Text(specialization, color = Color.White)
            }
        }
    }
}