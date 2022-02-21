package com.example.pokedexcompose.data.pojos.generations


import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)