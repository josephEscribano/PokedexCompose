package com.example.pokedexcompose.data.respositories

import com.example.pokedexcompose.data.sources.remote.RemoteDataSource
import com.example.pokedexcompose.domain.Pokemon
import com.example.pokedexcompose.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getPokemon(pokemonName: String): Flow<NetworkResult<Pokemon>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(remoteDataSource.getPokemon(pokemonName))
        }
    }

    suspend fun getNamesPokemonsByGeneration(nameGeneration: String): Flow<NetworkResult<List<String>>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(remoteDataSource.getNamesPokemonsByGeneration(nameGeneration))
        }
    }

    suspend fun pokemonList(nameGeneration: String): Flow<List<Pokemon>> {
        return flow<List<Pokemon>> {
            val listPokemon: MutableList<Pokemon> = mutableListOf()
            val listNames = remoteDataSource.getNamesPokemonsByGeneration(nameGeneration)
            if (listNames is NetworkResult.Succcess) {
                listNames.data?.forEach {
                    remoteDataSource.getPokemon(it).data?.let { pokemon -> listPokemon.add(pokemon) }
                }
            }
            emit(listPokemon)
        }.flowOn(Dispatchers.IO)
    }
}