package com.example.pokedexcompose.data.respositories

import com.example.pokedexcompose.data.sources.remote.RemoteDataSource
import com.example.pokedexcompose.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GenerationRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getGeneraciones(): Flow<NetworkResult<List<String>>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(remoteDataSource.getGeneraciones())
        }
    }


}