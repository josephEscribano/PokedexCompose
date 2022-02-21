package com.example.pokedexcompose.data.pojos.pokemons


import com.google.gson.annotations.SerializedName

data class TypeXX(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: TypeXXX
)