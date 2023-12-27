package com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase

import com.jlndev.movies.core.util.ResultData
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DeleteMovieFavoriteUseCaseImpl @Inject constructor(
    private val repository: MovieFavoriteRepository
) : DeleteMovieFavoriteUseCase {
    override suspend fun invoke(params: DeleteMovieFavoriteUseCase.Params): Flow<ResultData<Unit>> {
        return flow {
            try {
                val delete = repository.delete(params.movie)
                emit(ResultData.Success(delete))
            } catch (exception: Exception) {
                emit(ResultData.Error(exception))
            }
        }.flowOn(Dispatchers.IO)
    }
}