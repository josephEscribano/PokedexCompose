package com.example.pokedexcompose.framework.infopokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.pokedexcompose.utils.CargaDrawable
import com.example.pokedexcompose.utils.Constantes
import com.example.pokedexcompose.utils.parseStatToColor
import com.example.pokedexcompose.utils.parseTypeToColor
import kotlinx.coroutines.launch
import java.util.*


@Composable
fun ShowInfoPokemon(
    pokemonName: String,
    onBackNavigate: () -> Unit,
    viewModel: InfoPokemonViewModel = hiltViewModel()
) {
    val pokemon: Pokemon? = viewModel.pokemonState.collectAsState().value.pokemon
    LaunchedEffect(key1 = true) {
        viewModel.handleEvent(InfoPokemonContract.Event.getPokemon(pokemonName))
        pokemon?.let {
            viewModel.handleEvent(InfoPokemonContract.Event.checkPokemon(pokemon.id))
        }

        viewModel.handleEvent(InfoPokemonContract.Event.checkSizeList)

    }
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()
    val nPokemon: Int = viewModel.pokemonState.collectAsState().value.nPokemons
    val existe: Int = viewModel.pokemonState.collectAsState().value.existe

    val circularProgressDrawable = CargaDrawable()
    pokemon?.let {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState)
            },
            topBar = {
                TopAppBar(
                    title = { Text(text = Constantes.COMILLAS) },
                    backgroundColor = parseTypeToColor(pokemon.types.first()),
                    navigationIcon = { ArrowBackIcon(onBackNavigate) },
                    actions = {
                        if (existe == 0) {
                            IconButton(onClick = {
                                if (nPokemon < 6) {
                                    viewModel.handleEvent(
                                        InfoPokemonContract.Event.insertPokemon(
                                            pokemon
                                        )
                                    )
                                } else {
                                    scope.launch {
                                        snackBarHostState.showSnackbar(Constantes.AVISO_EQUIPO)
                                    }
                                }
                            }) {
                                LaunchedEffect(key1 = true) {
                                    viewModel.handleEvent(
                                        InfoPokemonContract.Event.checkPokemon(
                                            pokemon.id
                                        )
                                    )
                                }
                                if (existe == 0) {
                                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = null
                                    )
                                }

                            }
                        } else {
                            IconButton(onClick = {
                                viewModel.handleEvent(
                                    InfoPokemonContract.Event.deletePokemon(
                                        pokemon
                                    )
                                )
                            }) {
                                LaunchedEffect(key1 = true) {
                                    viewModel.handleEvent(
                                        InfoPokemonContract.Event.checkPokemon(
                                            pokemon.id
                                        )
                                    )
                                }
                                if (existe != 0) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = null
                                    )
                                } else {
                                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                                }

                            }
                        }

                    }
                )
            }
        ) {
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

                        Spacer(modifier = Modifier.size(10.dp))

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