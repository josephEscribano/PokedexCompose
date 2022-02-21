package com.example.pokedexcompose.data.sources.remote

import com.example.pokedexcompose.data.model.BaseApiResponse
import com.example.pokedexcompose.data.model.toPokemon
import com.example.pokedexcompose.data.sources.remote.apiinterface.PokemonService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val pokemonService: PokemonService
) : BaseApiResponse() {
    //Generaciones
    suspend fun getGeneraciones() =
        safeApiCall(apicall = { pokemonService.getNamesPokemonsByGeneration() }, transform = {
            it.results.map { result -> result.name }
        })

    suspend fun getNamesPokemonsByGeneration(nameGeneration: String) =
        safeApiCall(apicall = { pokemonService.getNamesPokemonsByGeneration(nameGeneration) },
            transform = {
                it.pokemonSpecies.map { pokemonSpecy -> pokemonSpecy.name }
            })


    //Pokemons

    suspend fun getPokemon(pokemonName: String) =
        safeApiCall(apicall = { pokemonService.getPokemon(pokemonName) }, transform = {
            it.toPokemon()
        })
}