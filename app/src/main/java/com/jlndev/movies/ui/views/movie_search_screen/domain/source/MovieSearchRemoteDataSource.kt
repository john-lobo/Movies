package com.jlndev.movies.ui.views.movie_search_screen.domain.source

import com.jlndev.movies.core.domain.model.MovieSearchPaging
import com.jlndev.movies.core.pagingsource.MovieSearchPagingSource

interface MovieSearchRemoteDataSource {
    suspend fun getSearchMovies(query: String, page: Int) : MovieSearchPaging

    fun getSearchMoviePagingSource(query: String) : MovieSearchPagingSource
}