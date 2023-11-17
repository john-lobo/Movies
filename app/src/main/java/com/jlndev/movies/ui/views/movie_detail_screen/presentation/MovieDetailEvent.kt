package com.jlndev.movies.ui.views.movie_detail_screen.presentation

import com.jlndev.movies.core.domain.model.Movie

sealed class MovieDetailEvent {
    data class GetMovieDetail(val movieId: Int) : MovieDetailEvent()
    data class AddFavorite(val movie: Movie) : MovieDetailEvent()
    data class RemoveFavorite(val movie: Movie) : MovieDetailEvent()
    data class CheckedFavorite(val movieId: Int) : MovieDetailEvent()
}