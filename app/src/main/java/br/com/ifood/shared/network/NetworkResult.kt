package br.com.ifood.shared.network

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error(val code: Int?, val message: String?) : NetworkResult<Nothing>()
    object NetworkException : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}

