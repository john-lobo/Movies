package com.jlndev.movies.ui.views.movie_search_screen.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingData
import com.jlndev.movies.core.domain.model.MovieSearch
import com.jlndev.movies.ui.views.movie_search_screen.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class GetMovieSearchUseCaseImpl @Inject constructor(private val repository: MovieSearchRepository) : GetMovieSearchUseCase {
    override fun invoke(params: GetMovieSearchUseCase.Params): Flow<PagingData<MovieSearch>> {
        return try {
            val pagingSource = repository.getPopularMovies(params.query)
            Pager(
                config = params.pagingConfig,
                pagingSourceFactory = { pagingSource }
            ).flow
        } catch (e: Exception) {
            emptyFlow()
        }
    }
}