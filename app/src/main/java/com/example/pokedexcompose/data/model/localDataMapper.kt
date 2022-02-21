package com.example.pokedexcompose.data.model

import com.example.pokedexcompose.data.model.entity.PokemonEntity
import com.example.pokedexcompose.domain.Pokemon


fun Pokemon.toPokemonEntity() : PokemonEntity {
    return PokemonEntity(id, name, imagen, types, estadisiticas, hablidades)
}

fun PokemonEntity.toPokemon() : Pokemon {
    return Pokemon(pokemonId, name,types, imagen, estadisiticas, hablidades)
}