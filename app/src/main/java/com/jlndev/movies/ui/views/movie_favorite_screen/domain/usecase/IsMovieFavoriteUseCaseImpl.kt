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
    override suspend fun invoke(params: IsMovieFavoriteUseCase.Params): Flow<ResultData<Boolean>> {
        return flow {
            try {
                val isFavorite = repository.isFavorite(params.movieId)
                emit(ResultData.Success(isFavorite))
            } catch (exception: Exception) {
                emit(ResultData.Error(exception))
            }
        }.flowOn(Dispatchers.IO)
    }
}