package com.vicadev.logogenerator.ui.content

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Compress
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.vicadev.logogenerator.LogoGeneratorViewModel
import com.vicadev.logogenerator.ui.component.ActionButton
import com.vicadev.logogenerator.ui.component.TitlteText

@Composable
fun InfoColumn(context: Context, viewModel: LogoGeneratorViewModel) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TitlteText("2. Introduce información adicional")

        ActionButton(
            text = if (viewModel.recording) "Detener Grabación" else "Iniciar Grabación",
            icon = if (viewModel.recording) Icons.Filled.MicOff else Icons.Filled.Mic,
            description = "Grabación de audio"
        ) {
            viewModel.recordAudio(context)
        }

        if (viewModel.info.isNotEmpty()) {
            ActionButton(
                text = "Resumir",
                icon = Icons.Filled.Compress,
                description = "Resumir grabación"
            ) {
                viewModel.createInfoSummary()
            }

            Text(text = viewModel.info)
        }
    }
}