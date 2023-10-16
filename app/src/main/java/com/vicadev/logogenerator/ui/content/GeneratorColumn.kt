package com.vicadev.logogenerator.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Draw
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.vicadev.logogenerator.ui.component.ActionButton
import com.vicadev.logogenerator.ui.component.TitlteText

@Composable
fun GeneratorColumn() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TitlteText("3. Generar el logo")

        ActionButton(
            text = "Generar Logo",
            icon = Icons.Filled.Draw,
            description = "Generar el logo") {
            //Acción del botón
        }
    }
}