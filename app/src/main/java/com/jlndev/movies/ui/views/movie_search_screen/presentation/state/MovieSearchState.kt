package com.jlndev.movies.ui.views.movie_search_screen.presentation.state

import androidx.paging.PagingData
import com.jlndev.movies.core.domain.model.MovieSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieSearchState (
    val query: String = "",
    val movies: Flow<PagingData<MovieSearch>> = emptyFlow()
)