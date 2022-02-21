package com.example.pokedexcompose.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Estadisitica(
    val nameStat: String,
    val valueStat : Int
) :Parcelable
