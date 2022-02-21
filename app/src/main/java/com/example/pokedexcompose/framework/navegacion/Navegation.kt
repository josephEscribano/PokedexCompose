package com.example.pokedexcompose.framework.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedexcompose.framework.equipo.MostrarEquipo
import com.example.pokedexcompose.framework.generaciones.MostrarGeneraciones
import com.example.pokedexcompose.framework.infopokemon.ShowInfoPokemon
import com.example.pokedexcompose.framework.listapokemons.ListPokemons
import com.example.pokedexcompose.utils.Constantes


@Composable
fun Navegation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.GENERACIONES
    ) {
        composable(
            route = Routes.GENERACIONES
        ) {
            MostrarGeneraciones(
                onNavigate = {
                    navController.navigate(Routes.LISTPOKEMON + Constantes.PANTALLA_GENERACIONES + it)
                })
        }
        composable(
            route = Routes.LISTPOKEMON + Constantes.PANTALLA_GENERACIONES_RECIBIR,
            arguments = listOf(
                navArgument(name = Constantes.GENERACION) {
                    type = NavType.StringType
                }
            )
        ) {
            ListPokemons(
                generationName = it.arguments?.get(Constantes.GENERACION) as String,
                onBackNavigate = { navController.popBackStack() }, onNavigate = { pokemonName ->
                    navController.navigate(Routes.POKEMON_DETALLES + Constantes.PANTALLA_LISTPOKEMON + pokemonName)
                },
                onNavigateEquipo = { navController.navigate(Routes.EQUIPO_SCREEN) }
            )
        }

        composable(
            route = Routes.POKEMON_DETALLES + Constantes.PANTALLA_LISTPOKEMON_RECIBIR,
            arguments = listOf(
                navArgument(name = Constantes.POKEMON_NAVIGATION) {
                    type = NavType.StringType
                }
            )) {
            ShowInfoPokemon(
                pokemonName = it.arguments?.get(Constantes.POKEMON_NAVIGATION) as String,
                onBackNavigate = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.EQUIPO_SCREEN
        ) {
            MostrarEquipo(onBackNavigate = { navController.popBackStack() })
        }


    }
}