package com.example.pokedexcompose.data.model

import com.example.pokedexcompose.data.model.entity.PokemonEntity
import com.example.pokedexcompose.data.model.entity.PokemonWithTipos
import com.example.pokedexcompose.data.model.entity.TipoEntity
import com.example.pokedexcompose.domain.Pokemon
import com.example.pokedexcompose.domain.Tipo


fun Pokemon.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(id, name, imagen)
}

fun PokemonEntity.toPokemon(): Pokemon {
    return Pokemon(pokemonId, name, emptyList(), imagen, emptyList(), emptyList())
}

fun PokemonWithTipos.toPokemon(): Pokemon {
    return Pokemon(
        pokemon.pokemonId,
        pokemon.name,
        tipos.map { it.toTipo() },
        pokemon.imagen,
        emptyList(),
        emptyList()
    )
}

fun Pokemon.toPokemonWithTipos(): PokemonWithTipos {
    return PokemonWithTipos(this.toPokemonEntity(), types.map { it.toTipoEntity() })
}

fun Tipo.toTipoEntity(): TipoEntity {
    return TipoEntity(slot, nameTipo)
}

fun TipoEntity.toTipo(): Tipo {
    return Tipo(slot, nameTipo)
}

