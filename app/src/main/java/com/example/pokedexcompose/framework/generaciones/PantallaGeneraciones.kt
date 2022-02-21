package com.example.pokedexcompose.framework.generaciones

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.pokedexcompose.R
import com.example.pokedexcompose.ui.theme.*
import com.example.pokedexcompose.utils.Constantes


@Composable
fun MostrarGeneraciones(
    viewModelGeneraciones: GeneracionesViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
    ) {

    viewModelGeneraciones.handleEvent(GeneracionesContract.Event.getGeneraciones)
    val generaciones = viewModelGeneraciones.generationState.collectAsState().value.generaciones
    Column(modifier = Modifier.background(LightBlue)) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(painter = painterResource(id = R.drawable.pokemontitulo)
            , contentDescription = Constantes.POKEMON,
            modifier = Modifier
                .fillMaxWidth())
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()

        ) {
            items(generaciones) { data ->
                Card(
                    modifier = Modifier
                        .padding(20.dp)

                        .shadow(5.dp, CircleShape),
                    elevation = 10.dp

                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onNavigate(data)
                            }
                            .background(TypeElectric)
                            .padding(5.dp)
                    ) {

                        Text(text = data)
                    }
                }
            }
        }
    }


}