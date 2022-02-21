package com.example.pokedexcompose.framework.infopokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.pokedexcompose.ui.theme.TypeWater
import com.example.pokedexcompose.utils.Constantes
import com.example.pokedexcompose.utils.parseStatToColor
import com.example.pokedexcompose.utils.parseTypeToColor
import java.util.*


@Composable
fun ShowInfoPokemon(
    pokemonName: String,
    onBackNavigate: () -> Unit,
    viewModel: InfoPokemonViewModel = hiltViewModel()
) {

    viewModel.handleEvent(InfoPokemonContract.Event.getPokemon(pokemonName))
    val pokemon: Pokemon? = viewModel.pokemonState.collectAsState().value.pokemon

    val context = LocalContext.current
    val circularProgressDrawable: CircularProgressDrawable = remember {
        val c = CircularProgressDrawable(context)
        c.strokeWidth = 5f
        c.centerRadius = 30f
        c.start()
        c
    }
    pokemon?.let {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "")},
                    backgroundColor = parseTypeToColor(pokemon.types.first()),
                    navigationIcon = { ArrowBackIcon(onBackNavigate) }
                )
            }
        ){
            Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(parseTypeToColor(pokemon.types.first()))
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth()
                        .padding(20.dp)
                        .shadow(10.dp, RoundedCornerShape(10.dp))
                        .align(CenterHorizontally)
                ) {

                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(5.dp)
                    ) {
                        ImagenAndName(pokemon, circularProgressDrawable)
                        TiposInfo(pokemon)
                        Spacer(
                            modifier = Modifier
                                .size(10.dp)
                        )
                        MostrarHabilidades(pokemon)

                        Spacer(
                            modifier = Modifier
                                .size(10.dp)
                        )

                        ListStats(pokemon)
                    }

                }
            }
        }
    }



}

@Composable
private fun ListStats(pokemon: Pokemon) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)

    ) {
        items(pokemon.estadisiticas) { data ->
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .shadow(5.dp, CircleShape),
                elevation = 10.dp

            ) {
                Box(
                    contentAlignment = Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(parseStatToColor(data))
                        .padding(5.dp)
                ) {
                    Text(text = data.nameStat + Constantes.DOS_PUNTOS + data.valueStat.toString())
                }
            }
        }
    }
}

@Composable
private fun MostrarHabilidades(pokemon: Pokemon) {
    Text(text = Constantes.HABILIDADES)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        for (habilidad in pokemon.hablidades) {
            Box(
                contentAlignment = Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
                    .clip(CircleShape)
                    .background(parseTypeToColor(pokemon.types.first()))
                    .height(35.dp)
            ) {
                Text(
                    text = habilidad.name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    },
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
private fun TiposInfo(pokemon: Pokemon) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        for (tipo in pokemon.types) {
            Box(
                contentAlignment = Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
                    .clip(CircleShape)
                    .background(parseTypeToColor(tipo))
                    .height(35.dp)
            ) {
                Text(
                    text = tipo.nameTipo.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    },
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
private fun ImagenAndName(
    pokemon: Pokemon,
    circularProgressDrawable: CircularProgressDrawable
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            Image(
                painter = rememberImagePainter(
                    data = pokemon.imagen,
                    builder = {
                        placeholder(circularProgressDrawable)
                        transformations(CircleCropTransformation())
                        crossfade(durationMillis = 2000)
                    }
                ),
                contentDescription = pokemon.name,
                modifier = Modifier
                    .size(200.dp)
                    .align(CenterHorizontally),
                contentScale = ContentScale.Fit
            )
            Text(
                text = pokemon.name,
                fontFamily = FontFamily.Monospace,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}