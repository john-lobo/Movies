package com.jlndev.movies.movie_popular_feature.domain.source

import com.jlndev.movies.core.data.remote.MovieService
import com.jlndev.movies.core.data.remote.response.MovieResponse
import com.jlndev.movies.core.page.MoviePagingSource

class MoviePopularRemoteDataSourceImpl(
    private val service: MovieService
) : MoviePopularRemoteDataSource {

    override suspend fun getPopularMovies(page: Int): MovieResponse {
        return service.getPopularMovies(page)
    }

    override fun getPopularMoviesPagingSource(): MoviePagingSource {
        return MoviePagingSource(this)
    }
}