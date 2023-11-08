package com.jlndev.movies.ui.views.movie_popular_screen.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jlndev.movies.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviePopularRepository {
    fun getPopularMovies(pagingConfig: PagingConfig) : Flow<PagingData<Movie>>
}