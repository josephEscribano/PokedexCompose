package com.example.pokedexcompose.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokedexcompose.domain.Tipo

@Entity(tableName = "equipoPokemon")
data class PokemonEntity(
    @PrimaryKey()
    var pokemonId: Int,
    var name: String,
    var imagen: String
)
