package com.jlndev.movies.ui.views.movie_popular_screen.presentation.state

import androidx.paging.PagingData
import com.jlndev.movies.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


data class MoviePopularState(
    val movies: Flow<PagingData<Movie>> = emptyFlow()
)