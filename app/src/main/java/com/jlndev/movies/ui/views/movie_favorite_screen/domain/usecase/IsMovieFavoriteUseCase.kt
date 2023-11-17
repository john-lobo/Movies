package com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase

import com.jlndev.movies.core.util.ResultData
import kotlinx.coroutines.flow.Flow

interface IsMovieFavoriteUseCase {
    suspend operator fun invoke(movieId: Int): Flow<ResultData<Boolean>>
}