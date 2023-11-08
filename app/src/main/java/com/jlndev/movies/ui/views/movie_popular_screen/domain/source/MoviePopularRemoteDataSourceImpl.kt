package com.jlndev.movies.ui.views.movie_popular_screen.domain.source

import com.jlndev.movies.core.data.remote.MovieService
import com.jlndev.movies.core.data.remote.response.MovieResponse
import com.jlndev.movies.core.pagingsource.MoviePagingSource
import javax.inject.Inject

class MoviePopularRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MoviePopularRemoteDataSource {

    override suspend fun getPopularMovies(page: Int): MovieResponse {
        return service.getPopularMovies(page)
    }

    override fun getPopularMoviesPagingSource(): MoviePagingSource {
        return MoviePagingSource(this)
    }
}