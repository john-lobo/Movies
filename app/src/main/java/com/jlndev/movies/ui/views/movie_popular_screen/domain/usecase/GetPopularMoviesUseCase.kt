package com.jlndev.movies.ui.views.movie_popular_screen.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jlndev.movies.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetPopularMoviesUseCase {
    operator fun invoke(params: Params): Flow<PagingData<Movie>>
    data class Params(val pagingConfig: PagingConfig)
}