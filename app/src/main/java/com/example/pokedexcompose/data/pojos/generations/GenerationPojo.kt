package com.example.pokedexcompose.data.pojos.generations


import com.google.gson.annotations.SerializedName

data class GenerationPojo(
    @SerializedName("abilities")
    val abilities: List<Any>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main_region")
    val mainRegion: MainRegion,
    @SerializedName("moves")
    val moves: List<Move>,
    @SerializedName("name")
    val name: String,
    @SerializedName("names")
    val names: List<Name>,
    @SerializedName("pokemon_species")
    val pokemonSpecies: List<PokemonSpecy>,
    @SerializedName("types")
    val types: List<Type>,
    @SerializedName("version_groups")
    val versionGroups: List<VersionGroup>
)