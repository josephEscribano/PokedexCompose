package com.example.pokedexcompose.data.sources.local

import com.example.pokedexcompose.data.model.entity.PokemonWithTipos
import com.example.pokedexcompose.data.room.dao.PokemonDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val pokemonDao: PokemonDao
) {
    fun getEquipo(): Flow<List<PokemonWithTipos>> = pokemonDao.getEquipo()

    fun checkPokemon(id: Int): Flow<Int> = pokemonDao.checkPokemon(id)

    fun checkSizeList(): Flow<Int> = pokemonDao.checkSizeList()

    suspend fun insertPokemonWithTipos(pokemonWithTipos: PokemonWithTipos) =
        pokemonDao.insertPokemonWithTipos(pokemonWithTipos)

    suspend fun deletePokemonWithTipos(pokemonWithTipos: PokemonWithTipos) =
        pokemonDao.deletePokemonWithTipos(pokemonWithTipos)
}