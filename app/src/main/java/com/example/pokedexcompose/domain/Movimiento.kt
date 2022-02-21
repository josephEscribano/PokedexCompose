package com.example.pokedexcompose.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movimiento(
    val level: Int,
    val name: String
) : Parcelable
