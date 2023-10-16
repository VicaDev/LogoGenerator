package com.vicadev.logogenerator.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.vicadev.logogenerator.ui.component.DataTextField
import com.vicadev.logogenerator.ui.component.TitlteText


@Composable
fun DataColumn(
    games: String,
    elements: String,
    onGamesChange: (String) -> Unit,
    onElementsChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TitlteText(text = "1. Completa los datos")
        DataTextField(label = "Introduce un juego", text = games, onValueChange = onGamesChange)
        DataTextField(label = "Elemento de referencia", text = elements, onValueChange = onElementsChange)
    }
}