package com.example.pokedexcompose.framework.listapokemons

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.example.pokedexcompose.ui.theme.TypeElectric
import com.example.pokedexcompose.ui.theme.TypeWater
import com.example.pokedexcompose.utils.Constantes
import com.example.pokedexcompose.utils.parseTypeToColor


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ListPokemons(
    onNavigate: (String) -> Unit,
    generationName: String,
    onBackNavigate : () -> Unit,
    viewModel: PokemonsViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.handleEvent(PokemonsContract.Event.getPokemonList(generationName))
    }
    val isLoading = viewModel.pokemonState.value.isLoading
    val listPokemon = viewModel.pokemonState.collectAsState().value.pokemonList
    val context = LocalContext.current
    val circularProgressDrawable: CircularProgressDrawable = remember {
        val c = CircularProgressDrawable(context)
        c.strokeWidth = 5f
        c.centerRadius = 30f
        c.start()
        c
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = Constantes.POKEMON
                    , color = Color.White )},
                backgroundColor = TypeWater,
                navigationIcon = {ArrowBackIcon(onBackNavigate)}
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .background(LightBlue)

        ) {
            items(listPokemon) { data ->
                Card(
                    modifier = Modifier
                        .padding(20.dp)
                        .shadow(5.dp, CircleShape),
                    elevation = 10.dp

                ) {
                    ImageAndName(data, onNavigate, circularProgressDrawable)
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
    onNavigate: (String) -> Unit,
    circularProgressDrawable: CircularProgressDrawable
) {
    Box(
        modifier = Modifier
            .background(parseTypeToColor(data.types.first()))
            .clickable {
                onNavigate(data.name)
            }
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
                    .align(CenterHorizontally),
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

