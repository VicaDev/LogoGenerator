package com.vicadev.logogenerator.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TitlteText(text: String) {
    Text(text.uppercase(), fontSize = 16.sp, fontWeight = FontWeight.Bold)
}