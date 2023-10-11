package com.vicadev.logogenerator.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vicadev.logogenerator.ui.component.DataTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataColumn(
    games: String,
    elements: String,
    onGamesChange: (String) -> Unit,
    onElementsChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = "1. Completa los datos")
        DataTextField(label = "Introduce un juego", text = games, onValueChange = onGamesChange)
        DataTextField(label = "Elemento de referencia", text = elements, onValueChange = onElementsChange)
    }
}