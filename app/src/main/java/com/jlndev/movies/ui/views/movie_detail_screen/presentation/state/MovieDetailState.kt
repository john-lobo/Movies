package com.jlndev.movies.ui.views.movie_detail_screen.presentation.state

import androidx.compose.ui.graphics.Color
import androidx.paging.PagingData
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.core.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieDetailState (
    val movieDetails: MovieDetails? = null,
    val error: String = "",
    val isLoading: Boolean = false,
    val iconColor: Color = Color.White,
    val results: Flow<PagingData<Movie>> = emptyFlow()
)