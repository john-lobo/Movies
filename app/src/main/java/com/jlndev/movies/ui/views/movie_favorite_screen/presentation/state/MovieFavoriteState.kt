package com.jlndev.movies.ui.views.movie_favorite_screen.presentation.state

import com.jlndev.movies.core.domain.model.Movie

data class MovieFavoriteState (
    val movies: List<Movie> = emptyList()
)