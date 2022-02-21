package com.example.pokedexcompose.data.sources.remote.apiinterface

import com.example.pokedexcompose.data.pojos.generaciones.GeneracionesPoJo
import com.example.pokedexcompose.data.pojos.generations.GenerationPojo
import com.example.pokedexcompose.data.pojos.pokemons.PokemonPojo
import com.example.pokedexcompose.utils.Constantes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET(Constantes.PATH_GENERACIONES)
    suspend fun getNamesPokemonsByGeneration() : Response<GeneracionesPoJo>

    @GET(Constantes.PATH_POKEMONSBYGEMERATIONS)
    suspend fun getNamesPokemonsByGeneration(@Path(Constantes.NAME_GENERATION) nameGeneration:String) : Response<GenerationPojo>

    @GET(Constantes.PATH_GETPOKEMONS)
    suspend fun getPokemon(@Path(Constantes.PARAMETER_POKEMONNAME) pokemonName : String) : Response<PokemonPojo>


}