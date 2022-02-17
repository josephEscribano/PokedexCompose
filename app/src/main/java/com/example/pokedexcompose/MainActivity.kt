package com.example.pokedexcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CambiarTexto(name = "", pass = "")
                }
            }
        }
    }
}


@Composable
fun CambiarTexto(name: String,pass: String ){
    var nameText by rememberSaveable { mutableStateOf(name) }
    var passText by rememberSaveable { mutableStateOf(name) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {
        PrimerCompose(onTextChange = {nameText = it}, name = nameText, pass = passText )
    }
}


@Composable
fun PrimerCompose(
    onTextChange: (String) -> Unit,
    pass: String,
    name: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(5.dp)) {
        OutlinedTextField(
            modifier = Modifier.border(width = Dp(2F) ,color = Color.Black),
            value = name,
            onValueChange = onTextChange)

        OutlinedTextField(
            modifier = Modifier.border(width = Dp(2F) ,color = Color.Black),
            value = pass,
            onValueChange = onTextChange)
    }


}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    PokedexComposeTheme {
//        PrimerCompose("Android")
//    }
//}