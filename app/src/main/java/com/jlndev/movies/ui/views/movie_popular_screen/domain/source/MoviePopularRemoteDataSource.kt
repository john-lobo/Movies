package com.jlndev.movies.ui.views.movie_popular_screen.domain.source

import com.jlndev.movies.core.data.remote.response.MovieResponse
import com.jlndev.movies.core.pagingsource.MoviePagingSource

interface MoviePopularRemoteDataSource {
    suspend fun getPopularMovies(page: Int) : MovieResponse

    fun getPopularMoviesPagingSource() : MoviePagingSource
}