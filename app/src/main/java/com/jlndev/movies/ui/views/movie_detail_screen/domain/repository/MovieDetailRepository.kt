package com.jlndev.movies.ui.views.movie_detail_screen.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.core.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    suspend fun getMovieDetails(movieId: Int) : MovieDetails
    suspend fun getMoviesSimilar(movieId: Int, pagingConfig: PagingConfig): Flow<PagingData<Movie>>
}