package com.example.pokedexcompose.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Generacion(
    val id: Int,
    val name: String,
    val pokemons: List<String>,
    val region: String
) : Parcelable
