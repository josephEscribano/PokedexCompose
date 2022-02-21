package com.example.pokedexcompose.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tipo(
    val slot: Int,
    val nameTipo: String
) : Parcelable
