package com.example.pokedexcompose.data.room.dao

import androidx.room.*
import com.example.pokedexcompose.data.model.entity.PokemonEntity
import com.example.pokedexcompose.data.model.entity.PokemonWithTipos
import com.example.pokedexcompose.data.model.entity.TipoEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface PokemonDao {
    @Transaction
    @Query("SELECT * FROM equipoPokemon")
    fun getEquipo() : Flow<List<PokemonWithTipos>>

    @Query("SELECT count(*) FROM equipoPokemon where pokemonId = :id")
    fun checkPokemon(id:Int) : Flow<Int>
    @Query("SELECT count(*) FROM equipoPokemon")
    fun checkSizeList() : Flow<Int>
    @Insert
    suspend fun insertPokemon(pokemonEntity: PokemonEntity) : Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTipo(listTipoEntity: List<TipoEntity>)
    @Transaction
    suspend fun insertPokemonWithTipos(pokemonWithTipos: PokemonWithTipos){
        pokemonWithTipos.pokemon.pokemonId = insertPokemon(pokemonWithTipos.pokemon).toInt()

        pokemonWithTipos.tipos.apply {
            forEach { it.idPokemon = pokemonWithTipos.pokemon.pokemonId }
            insertTipo(this)
        }
    }

    @Delete
    suspend fun deletePokemon(pokemonEntity: PokemonEntity)

    @Delete
    suspend fun deleteTipos(tipoEntity: TipoEntity)

    @Transaction
    suspend fun deletePokemonWithTipos(pokemonWithTipos: PokemonWithTipos){
        deletePokemon(pokemonWithTipos.pokemon)
        pokemonWithTipos.tipos.forEach {
            deleteTipos(it)
        }
    }

}