package com.example.pokedexcompose.framework.listapokemons

import com.example.pokedexcompose.domain.Pokemon

interface PokemonsContract {
    sealed class Event{
        data class getPokemon(val pokemonName: String) : Event()
        data class getNamesPokemonsByGeneration(val generationName: String) : Event()
        data class getPokemonList(val generationName: String) : Event()
    }

    data class StatePokemons(
        val pokemon: Pokemon? = null,
        val pokemonList : List<Pokemon> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    )

}