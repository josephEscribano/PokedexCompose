package com.example.pokedexcompose.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val id: Int,
    val name:String,
    val types: List<Tipo>,
    val imagen : String,
    val estadisiticas: List<Estadisitica>,
    val hablidades: List<Habilidad>,

    ) : Parcelable
