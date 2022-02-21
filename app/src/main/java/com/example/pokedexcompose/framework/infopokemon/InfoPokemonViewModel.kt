package com.example.pokedexcompose.framework.infopokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexcompose.data.respositories.PokemonsRepository
import com.example.pokedexcompose.framework.infopokemon.InfoPokemonContract.StateInfoPokemon
import com.example.pokedexcompose.utils.Constantes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoPokemonViewModel@Inject constructor(
    private val pokemonsRepository: PokemonsRepository
) : ViewModel()
 {

     private val _pokemonState : MutableStateFlow<StateInfoPokemon> by lazy {
         MutableStateFlow(StateInfoPokemon())
     }

     val pokemonState : StateFlow<StateInfoPokemon> = _pokemonState

     private val _error = Channel<String>()
     val error = _error.receiveAsFlow()
     fun handleEvent(event: InfoPokemonContract.Event){
         when(event){
             is InfoPokemonContract.Event.getPokemon -> {
                 viewModelScope.launch {
                     _pokemonState.update { it.copy(isLoading = true) }
                     pokemonsRepository.getPokemon(event.pokemonName).catch(action = { cause ->
                         _error.send(cause.message ?: Constantes.ERROR)
                     }).collect { result ->
                         _pokemonState.update {
                             it.copy(pokemon = result.data, isLoading = false) }

                     }

                 }
             }
         }
     }
}