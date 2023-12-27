package com.jlndev.movies.ui.views.movie_detail_screen.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingData
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.core.domain.model.MovieDetails
import com.jlndev.movies.core.util.ResultData
import com.jlndev.movies.ui.views.movie_detail_screen.domain.repository.MovieDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMovieDetailUseCaseImpl @Inject constructor(private val repository: MovieDetailRepository) : GetMovieDetailUseCase {

    override suspend fun invoke(params: GetMovieDetailUseCase.Params): ResultData<Pair<Flow<PagingData<Movie>>, MovieDetails>> {
        return withContext(Dispatchers.IO) {
            ResultData.Loading
            try {
                val pagingSource = repository.getMoviesSimilar(params.movieId)
                val movieDetails = repository.getMovieDetails(params.movieId)
                val pager = Pager(
                    config = params.pagingConfig,
                    pagingSourceFactory = {
                        pagingSource
                    }
                ).flow
                ResultData.Success(pager to movieDetails)
            } catch (e: Exception) {
                ResultData.Error(e)
            }
        }
    }
}