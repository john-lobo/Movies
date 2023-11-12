package com.jlndev.movies.ui.views.movie_detail_screen.domain.source

import com.jlndev.movies.core.data.remote.response.MovieResponse
import com.jlndev.movies.core.domain.model.MovieDetails
import com.jlndev.movies.core.pagingsource.MovieSimilarPagingSource

interface MovieDetailsRemoteDataSource {

    suspend fun getMovieDetails(movieId: Int) : MovieDetails
    suspend fun getMoviesSimilar(page: Int, movieId: Int) : MovieResponse
    fun getSimilarMoviesPagingSource(movieId: Int): MovieSimilarPagingSource
}