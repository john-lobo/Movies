package com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase

import com.jlndev.movies.core.domain.model.Movie
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
    override suspend fun invoke(movie: Movie): Flow<ResultData<Unit>> {
        return flow {
            val insert = repository.insert(movie)
            emit(ResultData.Success(insert))
        }.flowOn(Dispatchers.IO)
    }
}