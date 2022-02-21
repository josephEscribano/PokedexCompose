package com.example.pokedexcompose.framework.infopokemon

import com.example.pokedexcompose.domain.Pokemon

interface InfoPokemonContract {
    sealed class Event {
        data class getPokemon(val pokemonName: String) : Event()
    }

    data class StateInfoPokemon (
        val pokemon: Pokemon? = null,
        val isLoading: Boolean = false,
    )
}