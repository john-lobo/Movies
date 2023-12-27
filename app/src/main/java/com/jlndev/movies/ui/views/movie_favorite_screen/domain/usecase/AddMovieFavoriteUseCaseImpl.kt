package com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase

import com.jlndev.movies.core.util.ResultData
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddMovieFavoriteUseCaseImpl @Inject constructor(
    private val repository: MovieFavoriteRepository
) : AddMovieFavoriteUseCase {
    override suspend fun invoke(params: AddMovieFavoriteUseCase.Params): Flow<ResultData<Unit>> {
        return flow {
            try {
                val insert = repository.insert(params.movie)
                emit(ResultData.Success(insert))
            } catch (exception: Exception) {
                emit(ResultData.Error(exception))
            }
        }.flowOn(Dispatchers.IO)
    }
}