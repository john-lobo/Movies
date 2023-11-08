package com.jlndev.movies.ui.views.movie_search_screen.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jlndev.movies.core.domain.model.MovieSearch
import kotlinx.coroutines.flow.Flow

interface MovieSearchRepository {
    fun getPopularMovies(query: String, pagingConfig: PagingConfig) : Flow<PagingData<MovieSearch>>
}