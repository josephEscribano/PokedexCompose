package com.example.pokedexcompose.framework.equipo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.pokedexcompose.domain.Pokemon
import com.example.pokedexcompose.framework.shared.ArrowBackIcon
import com.example.pokedexcompose.ui.theme.LightBlue
import com.example.pokedexcompose.ui.theme.TypeWater
import com.example.pokedexcompose.utils.CargaDrawable
import com.example.pokedexcompose.utils.Constantes
import com.example.pokedexcompose.utils.parseTypeToColor

@Composable
fun MostrarEquipo(
    onBackNavigate: () -> Unit,
    viewModel: MostrarEquipoViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.handleEvent(MostrarEquipoContract.Event.getEquipo)
    }
    val isLoading = viewModel.uiState.collectAsState().value.isLoading
    val list = viewModel.uiState.collectAsState().value.equipo
    val circularProgressDrawable = CargaDrawable()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = Constantes.EQUIPO_POKEMON, color = Color.White
                    )
                },
                backgroundColor = TypeWater,
                navigationIcon = { ArrowBackIcon(onBackNavigate) }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .background(LightBlue)

        ) {
            items(list) { data ->
                Card(
                    modifier = Modifier
                        .padding(20.dp)
                        .shadow(5.dp, CircleShape),
                    elevation = 10.dp

                ) {
                    ImageAndName(data, circularProgressDrawable)
                }
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.Black)
            }
        }
    }


}

@Composable
private fun ImageAndName(
    data: Pokemon,
    circularProgressDrawable: CircularProgressDrawable
) {
    Box(
        modifier = Modifier
            .background(parseTypeToColor(data.types.first()))
            .fillMaxWidth()
    ) {
        Column {
            Image(
                painter = rememberImagePainter(
                    data = data.imagen,
                    builder = {
                        placeholder(circularProgressDrawable)
                        transformations(CircleCropTransformation())
                        crossfade(durationMillis = 2000)
                    }
                ),
                contentDescription = data.name,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Fit
            )
            Text(
                text = data.name,
                fontFamily = FontFamily.Monospace,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}