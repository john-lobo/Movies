package com.jlndev.movies.core.util


sealed class ResponseState<out T> {
    object Loading: ResponseState<Nothing>()
    data class Success<out T>(val data: T?) : ResponseState<T>()
    data class Error(val throwable: Exception?) : ResponseState<Nothing>()
}