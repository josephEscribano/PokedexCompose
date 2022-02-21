package com.example.pokedexcompose.framework.equipo

import com.example.pokedexcompose.domain.Pokemon

interface MostrarEquipoContract {

    sealed class Event() {
        object getEquipo : Event()
    }

    data class StateMostrarEquipo(
        val equipo : List<Pokemon> = emptyList(),
        val isLoading : Boolean = false
    )
}