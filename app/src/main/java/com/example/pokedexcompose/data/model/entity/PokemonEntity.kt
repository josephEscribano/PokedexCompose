package com.example.pokedexcompose.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokedexcompose.domain.Estadisitica
import com.example.pokedexcompose.domain.Habilidad
import com.example.pokedexcompose.domain.Tipo

@Entity(tableName = "pokemons")
data class PokemonEntity(
    @PrimaryKey()
    var pokemonId:Int,
    var name: String,
    var imagen: String,
    val types: List<Tipo>,
    val estadisiticas: List<Estadisitica>,
    val hablidades: List<Habilidad>,
    var idEquipo: Int = 0,
)
