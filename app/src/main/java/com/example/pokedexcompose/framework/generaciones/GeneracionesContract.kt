package com.example.pokedexcompose.framework.generaciones

import com.example.pokedexcompose.domain.Generacion

interface GeneracionesContract {

    sealed class Event {
        object getGeneraciones : Event()
    }

    data class StateMostrarGenerations(
        val generaciones: List<String> = emptyList(),
        val generation: Generacion?  = null,
        val isLoading: Boolean = false,
        val error: String? = null,
    )
}