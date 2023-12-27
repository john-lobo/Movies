package com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase

import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class GetMoviesFavoriteUseCaseImpl @Inject constructor(
    private val repository: MovieFavoriteRepository
) : GetMoviesFavoriteUseCase {
    override suspend fun invoke(): Flow<List<Movie>> {
        return try {
            repository.getMovies()
        } catch (e: Exception) {
            emptyFlow()
        }
    }
}