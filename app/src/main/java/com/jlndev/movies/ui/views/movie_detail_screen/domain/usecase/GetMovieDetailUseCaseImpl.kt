package com.jlndev.movies.ui.views.movie_detail_screen.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.core.domain.model.MovieDetails
import com.jlndev.movies.core.util.ResultData
import com.jlndev.movies.ui.views.movie_detail_screen.domain.repository.MovieDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieDetailUseCaseImpl @Inject constructor(private val respository: MovieDetailRepository) :
    GetMovieDetailUseCase {
    override fun invoke(movieId: Int): Flow<ResultData<Pair<Flow<PagingData<Movie>>, MovieDetails>>> {
        return flow {
            try {
                emit(ResultData.Loading)
                val movieDetails = respository.getMovieDetails(movieId)
                val moviesSimilar = respository.getMoviesSimilar(
                    movieId,
                    PagingConfig(
                        pageSize = 20,
                        initialLoadSize = 20,
                    )
                )
                emit(ResultData.Success(Pair(moviesSimilar, movieDetails)))
            } catch (e: Exception) {
                emit(ResultData.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}