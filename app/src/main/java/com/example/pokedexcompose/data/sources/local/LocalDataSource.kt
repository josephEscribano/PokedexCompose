package com.example.pokedexcompose.data.sources.local

import com.example.pokedexcompose.data.room.dao.PokemonDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val pokemonDao: PokemonDao
) {

}