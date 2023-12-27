package com.jlndev.movies.ui.views.movie_search_screen.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jlndev.movies.core.domain.model.MovieSearch
import kotlinx.coroutines.flow.Flow

interface GetMovieSearchUseCase {
    operator fun invoke(params: Params): Flow<PagingData<MovieSearch>>
    data class Params(val query: String, val pagingConfig: PagingConfig)
}