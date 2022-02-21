package com.example.pokedexcompose.data.sources.remote.di


import android.os.Build
import androidx.annotation.RequiresApi
import com.example.pokedexcompose.data.sources.remote.apiinterface.PokemonService
import com.example.pokedexcompose.utils.Constantes.BASE_URL
import com.google.gson.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.time.LocalDate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun getGson(): Gson {
        return GsonBuilder().registerTypeAdapter(
            LocalDate::class.java,
            JsonDeserializer { jsonElement: JsonElement, type: Type?, jsonDeserializationContext: JsonDeserializationContext? ->
                LocalDate.parse(
                    jsonElement.asJsonPrimitive.asString
                )
            } as JsonDeserializer<LocalDate>)
            .registerTypeAdapter(
                LocalDate::class.java,
                JsonSerializer { localDate: LocalDate, type: Type?, jsonSerializationContext: JsonSerializationContext? ->
                    JsonPrimitive(
                        localDate.toString()
                    )
                } as JsonSerializer<LocalDate>).create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun pokemonService(retrofit: Retrofit): PokemonService =
        retrofit.create(PokemonService::class.java)

}