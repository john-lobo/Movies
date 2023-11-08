package com.jlndev.movies.ui.views.movie_search_screen.domain.usecase

import androidx.paging.PagingData
import com.jlndev.movies.core.domain.model.MovieSearch
import kotlinx.coroutines.flow.Flow

interface GetMovieSearchUse {
    operator fun invoke(query: String): Flow<PagingData<MovieSearch>>
}