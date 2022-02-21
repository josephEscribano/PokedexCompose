package com.example.pokedexcompose.framework.generaciones

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexcompose.data.respositories.GenerationRepository
import com.example.pokedexcompose.domain.Generacion
import com.example.pokedexcompose.framework.generaciones.GeneracionesContract.StateMostrarGenerations
import com.example.pokedexcompose.utils.Constantes
import com.example.pokedexcompose.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneracionesViewModel @Inject constructor(private val generationRepository: GenerationRepository) : ViewModel(){


    private val _generationState : MutableStateFlow<StateMostrarGenerations> by lazy {
        MutableStateFlow(StateMostrarGenerations())
    }

    val generationState : StateFlow<StateMostrarGenerations> = _generationState


    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()


    fun handleEvent(event : GeneracionesContract.Event){
        when(event){
            GeneracionesContract.Event.getGeneraciones -> {
                viewModelScope.launch {
                    generationRepository.getGeneraciones().catch(action = { cause ->
                        _error.send(cause.message ?: Constantes.ERROR)
                    }).collect { result ->
                        when(result){
                            is NetworkResult.Error -> {
                                _generationState.update { it.copy(error = result.message) }
                            }
                            is NetworkResult.Loading -> _generationState.update { it.copy(isLoading = true) }
                            is NetworkResult.Succcess -> _generationState.update {

                                it.copy(
                                    generaciones = result.data!!,
                                    isLoading = false
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}