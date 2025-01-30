package br.com.ifood.shared.network

import kotlinx.coroutines.flow.Flow

abstract class NetworkDataSource<T> {

    abstract suspend fun fetchData(): Flow<NetworkResult<T>>

    protected suspend fun fetchFromNetwork(apiCall: suspend () -> T): NetworkResult<T> {
        return safeApiCall { apiCall() }
    }
}
