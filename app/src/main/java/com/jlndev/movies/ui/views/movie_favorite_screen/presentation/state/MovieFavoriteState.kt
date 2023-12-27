package com.jlndev.movies.ui.views.movie_favorite_screen.presentation.state

import com.jlndev.movies.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieFavoriteState (
    val movies: Flow<List<Movie>> = emptyFlow()
)