package com.jlndev.movies.core.util


sealed class ResultData<out T> {
    object Loading: ResultData<Nothing>()
    data class Success<out T>(val data: T?) : ResultData<T>()
    data class Error(val throwable: Exception?) : ResultData<Nothing>()
}