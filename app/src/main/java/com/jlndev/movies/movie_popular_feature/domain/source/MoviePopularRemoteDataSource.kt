package com.jlndev.movies.movie_popular_feature.domain.source

import com.jlndev.movies.core.data.remote.response.MovieResponse
import com.jlndev.movies.core.page.MoviePagingSource

interface MoviePopularRemoteDataSource {
    suspend fun getPopularMovies(page: Int) : MovieResponse

    fun getPopularMoviesPagingSource() : MoviePagingSource
}