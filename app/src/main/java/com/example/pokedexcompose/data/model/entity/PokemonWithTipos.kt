package com.example.pokedexcompose.data.model.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PokemonWithTipos(
    @Embedded val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "idPokemon"
    )
    val tipos : List<TipoEntity>
)
