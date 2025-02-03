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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip


@Composable
fun ClassSelectionScreen (
    navController: NavController,
    onClassSelected: (String) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Selecciona una clase",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        //Show classes list with color asociated
        classColors.forEach{ (className, color) ->
            Button(
                onClick = {
                    onClassSelected(className) //Callback to selected class
                    navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, SolidColor(Color.Black), RoundedCornerShape(32.dp)).clip(RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = color, //Button background
                    contentColor = Color.Black
                )
            ) {
                Text(className)
            }
        }
    }
}