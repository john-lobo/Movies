package com.jlndev.movies.ui.views.movie_search_screen.domain.source

import com.jlndev.movies.core.data.remote.MovieService
import com.jlndev.movies.core.domain.model.MovieSearchPaging
import com.jlndev.movies.core.pagingsource.MovieSearchPagingSource
import com.jlndev.movies.core.util.ext.toMoviesSearch
import javax.inject.Inject

class MovieSearchRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MovieSearchRemoteDataSource {
    override suspend fun getSearchMovies(query: String, page: Int): MovieSearchPaging {

        val response = service.searchMovie(page = page, query = query)
        return MovieSearchPaging(
            response.page,
            response.totalPages,
            response.totalResults,
            response.results.toMoviesSearch()
        )
    }

    override fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource {
        return MovieSearchPagingSource(query, this)
    }

}