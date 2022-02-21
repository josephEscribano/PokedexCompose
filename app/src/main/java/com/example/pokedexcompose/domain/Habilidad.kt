package com.example.pokedexcompose.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Habilidad(
    val slot: Int,
    val name: String
) : Parcelable
