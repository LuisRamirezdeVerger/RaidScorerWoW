package com.wito.raidscorerapp.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.wito.raidscorerapp.R

@Composable
fun ClassDropdown(
    selectedClass: String,
    onClassSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    //Dropdown Menu Trigger
    Box {
        OutlinedTextField(
            value = selectedClass,
            onValueChange = {}, //There's no manual change
            readOnly = true,
            label = { Text("Clase") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            classColors.keys.forEach { clase ->
                DropdownMenuItem(
                    text = { Text(clase) },
                    onClick = {
                        onClassSelected(clase)
                        expanded = false
                    }
                )
            }
        }
    }
}