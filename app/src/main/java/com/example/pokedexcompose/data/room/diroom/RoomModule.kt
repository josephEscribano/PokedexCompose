package com.example.pokedexcompose.data.room.diroom

import android.content.Context
import androidx.room.Room
import com.example.pokedexcompose.R
import com.example.pokedexcompose.data.room.PokemonRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            PokemonRoomDatabase::class.java,
            context.getString(R.string.db)
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    fun equipoDao(pokemonRoomDatabase: PokemonRoomDatabase) = pokemonRoomDatabase.pokemonDao()

}