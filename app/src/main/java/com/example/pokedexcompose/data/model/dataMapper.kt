package com.example.pokedexcompose.data.model

import com.example.pokedexcompose.data.pojos.generaciones.GeneracionesPoJo
import com.example.pokedexcompose.data.pojos.generations.GenerationPojo
import com.example.pokedexcompose.data.pojos.generations.PokemonSpecy
import com.example.pokedexcompose.data.pojos.pokemons.*
import com.example.pokedexcompose.domain.*


fun GenerationPojo.toGeneration() : Generacion {
    return Generacion(id,name, pokemonSpecies.map { it.name },mainRegion.name)
}

fun PokemonPojo.toPokemon() : Pokemon {
    return Pokemon(id, name,types.map { it.toTipo() },sprites.other.officialArtwork.frontDefault
        ,stats.map { it.toEstadistica() },abilities.map { it.toHabilidad() })
}

fun TypeXX.toTipo() : Tipo {
    return Tipo(slot,type.name)
}

fun Stat.toEstadistica() : Estadisitica {
    return Estadisitica(stat.name,baseStat)
}

fun Ability.toHabilidad() : Habilidad {
    return Habilidad(slot,ability.name)
}


