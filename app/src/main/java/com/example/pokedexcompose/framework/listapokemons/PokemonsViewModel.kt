package com.example.pokedexcompose.framework.listapokemons

import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexcompose.data.respositories.PokemonsRepository
import com.example.pokedexcompose.domain.Pokemon
import com.example.pokedexcompose.framework.listapokemons.PokemonsContract.StatePokemons
import com.example.pokedexcompose.utils.Constantes
import com.example.pokedexcompose.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(
    private val pokemonsRepository: PokemonsRepository
) : ViewModel(){
    private val _pokemonState : MutableStateFlow<StatePokemons> by lazy {
        MutableStateFlow(StatePokemons())
    }

    val pokemonState : StateFlow<StatePokemons> = _pokemonState

    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()

    fun handleEvent(event: PokemonsContract.Event){
        when(event){
            is PokemonsContract.Event.getPokemonList -> {
                viewModelScope.launch {
                    _pokemonState.update { it.copy(isLoading = true) }
                    pokemonsRepository.pokemonList(event.generationName).catch(action = { cause ->
                        _error.send(cause.message ?: Constantes.ERROR)
                    }).collect { result ->

                        _pokemonState.update { it.copy(pokemonList = result, isLoading = false) }

                    }

                }

            }

        }

    }
}