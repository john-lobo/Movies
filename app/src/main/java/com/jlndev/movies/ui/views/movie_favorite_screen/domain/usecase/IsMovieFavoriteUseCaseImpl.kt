package com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase

import com.jlndev.movies.core.util.ResultData
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class IsMovieFavoriteUseCaseImpl @Inject constructor(
    private val repository: MovieFavoriteRepository
) : IsMovieFavoriteUseCase {
    override suspend fun invoke(movieId: Int): Flow<ResultData<Boolean>> {
        return flow {
            val isFavorite = repository.isFavorite(movieId)
            emit(ResultData.Success(isFavorite))
        }.flowOn(Dispatchers.IO)
    }
}