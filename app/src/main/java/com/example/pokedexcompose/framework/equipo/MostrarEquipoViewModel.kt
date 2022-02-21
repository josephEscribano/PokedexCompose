package com.example.pokedexcompose.framework.equipo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexcompose.data.respositories.EquipoRepository
import com.example.pokedexcompose.framework.equipo.MostrarEquipoContract.StateMostrarEquipo
import com.example.pokedexcompose.utils.Constantes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MostrarEquipoViewModel @Inject constructor(private val equipoRepository: EquipoRepository) :
    ViewModel() {


    private val _uiState: MutableStateFlow<StateMostrarEquipo> by lazy {
        MutableStateFlow(StateMostrarEquipo())
    }
    val uiState: StateFlow<StateMostrarEquipo> = _uiState

    private val _error = Channel<String>()

    fun handleEvent(event: MostrarEquipoContract.Event) {
        when (event) {
            MostrarEquipoContract.Event.getEquipo -> {
                viewModelScope.launch {
                    _uiState.update { it.copy(isLoading = true) }
                    equipoRepository.getEquipo().catch(action = { cause ->
                        _error.send(cause.message ?: Constantes.ERROR)
                    }).collect {
                        _uiState.update { stateMostrarEquipo ->
                            stateMostrarEquipo.copy(
                                equipo = it,
                                isLoading = false
                            )
                        }
                    }
                }
            }

        }
    }
}
