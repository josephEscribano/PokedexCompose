package com.example.pokedexcompose.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokedexcompose.data.model.entity.EquipoEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface PokemonDao {

    @Query("SELECT * FROM equipoPokemon")
    suspend fun getEquipo() : Flow<List<EquipoEntity>>

    @Insert
    suspend fun insertPokemon(equipoEntity: EquipoEntity)
}