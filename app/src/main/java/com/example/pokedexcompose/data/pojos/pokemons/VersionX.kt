package com.example.pokedexcompose.data.pojos.pokemons


import com.google.gson.annotations.SerializedName

data class VersionX(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)