package com.jlndev.movies.ui.views.movie_search_screen.domain.source

import com.jlndev.movies.core.data.remote.MovieService
import com.jlndev.movies.core.data.remote.response.SearchResponse
import com.jlndev.movies.core.pagingsource.MovieSearchPagingSource
import javax.inject.Inject

class MovieSearchRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MovieSearchRemoteDataSource {
    override suspend fun getSearchMovies(query: String, page: Int): SearchResponse {
        return service.searchMovie(query, page)
    }

    override fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource {
        return MovieSearchPagingSource(query, this)
    }

}