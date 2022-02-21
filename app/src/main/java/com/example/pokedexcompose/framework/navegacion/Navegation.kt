package com.example.pokedexcompose.framework.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedexcompose.domain.Pokemon
import com.example.pokedexcompose.framework.generaciones.MostrarGeneraciones
import com.example.pokedexcompose.framework.infopokemon.ShowInfoPokemon
import com.example.pokedexcompose.framework.listapokemons.ListPokemons


@Composable
fun Navegation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.GENERACIONES
    ){
        composable(
            route = Routes.GENERACIONES
        ){
            MostrarGeneraciones(onNavigate = {
                navController.navigate(Routes.LISTPOKEMON + "?generacion=${it}")
            })
        }
        composable(
            route = Routes.LISTPOKEMON + "?generacion={generacion}",
            arguments = listOf(
                navArgument(name = "generacion"){
                    type = NavType.StringType
                }
            )
        ){
          ListPokemons(
              generationName = it.arguments?.get("generacion") as String,
              onBackNavigate = {navController.popBackStack()}
              , onNavigate = { pokemonName ->
                  navController.navigate(Routes.POKEMON_DETALLES + "?pokemon=${pokemonName}")
              })
        }

        composable(
            route = Routes.POKEMON_DETALLES + "?pokemon={pokemon}",
            arguments = listOf(
                navArgument(name = "pokemon"){
                    type = NavType.StringType
                }
        )){
            ShowInfoPokemon(pokemonName = it.arguments?.get("pokemon") as String,
            onBackNavigate = {navController.popBackStack()})
        }
    }
}