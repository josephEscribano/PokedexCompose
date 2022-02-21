package com.example.pokedexcompose.framework.infopokemon

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexcompose.data.respositories.EquipoRepository
import com.example.pokedexcompose.data.respositories.PokemonsRepository
import com.example.pokedexcompose.framework.infopokemon.InfoPokemonContract.StateInfoPokemon
import com.example.pokedexcompose.utils.Constantes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoPokemonViewModel @Inject constructor(
    private val pokemonsRepository: PokemonsRepository,
    private val equipoRepository: EquipoRepository
) : ViewModel() {

    private val _pokemonState: MutableStateFlow<StateInfoPokemon> by lazy {
        MutableStateFlow(StateInfoPokemon())
    }

    val pokemonState: StateFlow<StateInfoPokemon> = _pokemonState

    private val _error = Channel<String>()

    fun handleEvent(event: InfoPokemonContract.Event) {
        when (event) {
            is InfoPokemonContract.Event.getPokemon -> {
                viewModelScope.launch {
                    _pokemonState.update { it.copy(isLoading = true) }
                    pokemonsRepository.getPokemon(event.pokemonName).catch(action = { cause ->
                        _error.send(cause.message ?: Constantes.ERROR)
                    }).collect { result ->
                        _pokemonState.update {
                            it.copy(pokemon = result.data, isLoading = false)
                        }

                    }

                }
            }

            is InfoPokemonContract.Event.insertPokemon -> {
                viewModelScope.launch {
                    try {
                        equipoRepository.insertPokemonWithTipos(event.pokemon)
                    } catch (e: Exception) {
                        Log.e(Constantes.ERROR_INSERTAR, e.message, e)
                        _error.send(e.message ?: Constantes.ERROR)
                    }
                }
            }
            is InfoPokemonContract.Event.checkPokemon -> {
                viewModelScope.launch {
                    equipoRepository.checkPokemon(event.id).catch(action = { cause ->
                        _error.send(cause.message ?: Constantes.ERROR)
                    }).collect { result ->
                        _pokemonState.update { it.copy(existe = result) }
                    }
                }
            }
            is InfoPokemonContract.Event.deletePokemon -> {
                viewModelScope.launch {
                    try {
                        equipoRepository.deletePokemonWithTipos(event.pokemon)
                    } catch (e: Exception) {
                        Log.e(Constantes.ERROR_DELETE, e.message, e)
                        _error.send(e.message ?: Constantes.ERROR)
                    }

                }
            }
            InfoPokemonContract.Event.checkSizeList -> {
                viewModelScope.launch {
                    equipoRepository.checkSizeList().catch(action = { cause ->
                        _error.send(cause.message ?: Constantes.ERROR)
                    }).collect { result ->
                        _pokemonState.update { it.copy(nPokemons = result) }
                    }
                }
            }
        }
    }
}