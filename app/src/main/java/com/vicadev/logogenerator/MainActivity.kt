package com.vicadev.logogenerator

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.vicadev.logogenerator.ui.content.DataColumn
import com.vicadev.logogenerator.ui.content.GeneratorColumn
import com.vicadev.logogenerator.ui.content.InfoColumn
import com.vicadev.logogenerator.ui.theme.LogoGeneratorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 0)

        super.onCreate(savedInstanceState)
        setContent {
            LogoGeneratorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content() {

    val viewModel = LogoGeneratorViewModel()
    val scrollState = rememberScrollState()

    var games by remember { mutableStateOf("") }
    var elements by remember { mutableStateOf("") }



    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Generador de Logos") })
        }
    ) { padding ->

        if (viewModel.loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .alpha(if (viewModel.loading) 0.5f else 1f)
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            //Bloque 1
            DataColumn(
                games,
                elements,
                onGamesChange = { games = it },
                onElementsChange = { elements = it }
            )

            //Bloque 2
            InfoColumn(LocalContext.current, viewModel)

            //Bloque 3
            GeneratorColumn()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    LogoGeneratorTheme {
        Content()
    }
}