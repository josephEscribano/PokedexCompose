package com.example.pokedexcompose.data.respositories

import com.example.pokedexcompose.data.model.toPokemon
import com.example.pokedexcompose.data.model.toPokemonEntity
import com.example.pokedexcompose.data.model.toPokemonWithTipos
import com.example.pokedexcompose.data.sources.local.LocalDataSource
import com.example.pokedexcompose.domain.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EquipoRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    fun getEquipo() : Flow<List<Pokemon>>{
        return localDataSource.getEquipo().map {
            it.map { pokemonWithTipos -> pokemonWithTipos.toPokemon() }
        }.flowOn(Dispatchers.IO)
    }

    fun checkPokemon(id:Int) : Flow<Int> {
        return localDataSource.checkPokemon(id).flowOn(Dispatchers.IO)
    }

    fun checkSizeList() : Flow<Int> {
        return localDataSource.checkSizeList().flowOn(Dispatchers.IO)
    }
    suspend fun insertPokemonWithTipos(pokemon: Pokemon) =
        withContext(Dispatchers.IO) {
            localDataSource.insertPokemonWithTipos(pokemon.toPokemonWithTipos())
        }

    suspend fun deletePokemonWithTipos(pokemon: Pokemon) =
        withContext(Dispatchers.IO){
            localDataSource.deletePokemonWithTipos(pokemon.toPokemonWithTipos())
        }

}