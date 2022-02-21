package com.example.pokedexcompose.data.pojos.generaciones


import com.google.gson.annotations.SerializedName

data class GeneracionesPoJo(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: Any,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
)