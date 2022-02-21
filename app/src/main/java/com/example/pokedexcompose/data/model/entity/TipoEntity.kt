package com.example.pokedexcompose.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tipos")
data class TipoEntity(

    val slot: Int,
    val nameTipo : String,
    var idPokemon: Int = 0,
    @PrimaryKey(autoGenerate = true)
    val idTipo: Int = 0,
)
