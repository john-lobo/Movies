package com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase

import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.core.util.ResultData
import kotlinx.coroutines.flow.Flow

interface AddMovieFavoriteUseCase {
    suspend operator fun invoke(movie: Movie): Flow<ResultData<Unit>>
}