package com.example.pokedexcompose.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "equuipoPokemon")
data class PokemonEntity(
    @PrimaryKey()
    var pokemonId: Int,
    var name: String,
    var imagen: String
)
