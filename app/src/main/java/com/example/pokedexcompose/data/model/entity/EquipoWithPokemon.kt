package com.example.pokedexcompose.data.model.entity

import androidx.room.Embedded
import androidx.room.Relation

data class EquipoWithPokemon(
    @Embedded val equipo: EquipoEntity,
    @Relation(
        parentColumn = "idEquipoPokemon",
        entityColumn = "idEquipo"
    )
    val pokemons: List<PokemonEntity>
)