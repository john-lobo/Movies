package com.jlndev.movies.ui.views.movie_popular_screen.domain.source

import com.jlndev.movies.core.data.remote.MovieService
import com.jlndev.movies.core.domain.model.MoviePaging
import com.jlndev.movies.core.pagingsource.MoviePagingSource
import com.jlndev.movies.core.util.ext.toMovies
import javax.inject.Inject

class MoviePopularRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MoviePopularRemoteDataSource {

    override suspend fun getPopularMovies(page: Int): MoviePaging {
        val response  = service.getPopularMovies(page)
        return MoviePaging(
            page = response.page,
            totalPages = response.totalPages,
            totalResults = response.totalResults,
            movies = response.results.toMovies()
        )
    }

    override fun getPopularMoviesPagingSource(): MoviePagingSource {
        return MoviePagingSource(this)
    }
}