package com.example.pokedexcompose.framework.equipo

import androidx.lifecycle.ViewModel
import com.example.pokedexcompose.data.respositories.GenerationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MostrarEquipoViewModel @Inject constructor(private val generationRepository: GenerationRepository) : ViewModel(){

}
