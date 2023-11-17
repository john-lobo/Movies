package com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase

import com.jlndev.movies.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetMoviesFavoriteUseCase {
    suspend operator fun invoke(): Flow<List<Movie>>
}