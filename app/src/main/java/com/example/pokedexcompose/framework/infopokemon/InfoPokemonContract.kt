package com.example.pokedexcompose.framework.infopokemon

import com.example.pokedexcompose.domain.Pokemon
import com.example.pokedexcompose.framework.equipo.MostrarEquipoContract

interface InfoPokemonContract {
    sealed class Event {
        data class getPokemon(val pokemonName: String) : Event()
        data class insertPokemon(val pokemon:Pokemon ) : Event()
        data class checkPokemon(val id:Int) : Event()
        data class deletePokemon(val pokemon: Pokemon) : Event()
        object checkSizeList : Event()
    }

    data class StateInfoPokemon (
        val pokemon: Pokemon? = null,
        val nPokemons : Int = 0,
        val existe: Int = 0,
        val isLoading: Boolean = false,
    )
}