package com.jlndev.movies.ui.views.movie_search_screen.domain.source

import com.jlndev.movies.core.data.remote.response.SearchResponse
import com.jlndev.movies.core.pagingsource.MovieSearchPagingSource

interface MovieSearchRemoteDataSource {
    suspend fun getSearchMovies(query: String, page: Int) : SearchResponse

    fun getSearchMoviePagingSource(query: String) : MovieSearchPagingSource
}