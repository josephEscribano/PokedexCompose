package com.example.pokedexcompose.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokedexcompose.data.model.entity.PokemonEntity
import com.example.pokedexcompose.data.model.entity.TipoEntity
import com.example.pokedexcompose.data.room.dao.PokemonDao


@Database(
    entities = [PokemonEntity::class,TipoEntity::class],
    version = 7,
    exportSchema = true
)
abstract class PokemonRoomDatabase : RoomDatabase() {

    abstract fun pokemonDao() : PokemonDao
}