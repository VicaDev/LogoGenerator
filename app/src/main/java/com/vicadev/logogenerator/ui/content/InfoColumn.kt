package com.vicadev.logogenerator.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Compress
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.vicadev.logogenerator.ui.component.ActionButton
import com.vicadev.logogenerator.ui.component.TitlteText

@Composable
fun InfoColumn() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TitlteText("2. Introduce información adicional")

        ActionButton(
            text = "Iniciar grabación",
            icon = Icons.Filled.Mic,
            description = "Iniciar grabación"
        ) {
            //Acción del botón
        }
        ActionButton(
            text = "Resumir",
            icon = Icons.Filled.Compress,
            description = "Resumir grabación"
        ) {
            //Acción del botón
        }
    }
}