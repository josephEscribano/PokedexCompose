package com.example.pokedexcompose.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokedexcompose.data.model.entity.EquipoEntity
import com.example.pokedexcompose.data.model.entity.PokemonEntity
import com.example.pokedexcompose.data.room.dao.PokemonDao


@Database(
    entities = [PokemonEntity::class,EquipoEntity::class],
    version = 1,
    exportSchema = true
)
abstract class PokemonRoomDatabase : RoomDatabase() {

    abstract fun pokemonDao() : PokemonDao
}