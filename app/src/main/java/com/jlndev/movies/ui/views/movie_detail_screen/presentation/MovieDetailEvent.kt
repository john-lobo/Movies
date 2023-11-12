package com.jlndev.movies.ui.views.movie_detail_screen.presentation

sealed class MovieDetailEvent {
    data class GetMovieDetail(val movieId: Int) : MovieDetailEvent()
}