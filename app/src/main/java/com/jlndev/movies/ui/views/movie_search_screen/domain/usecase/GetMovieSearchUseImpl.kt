package com.jlndev.movies.ui.views.movie_search_screen.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jlndev.movies.core.domain.model.MovieSearch
import com.jlndev.movies.ui.views.movie_search_screen.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieSearchUseImpl @Inject constructor(private val repository: MovieSearchRepository) : GetMovieSearchUse {
    override fun invoke(query: String): Flow<PagingData<MovieSearch>> {
        return repository.getPopularMovies(
            query = query,
            pagingConfig = PagingConfig(
                pageSize = 20 ,
                initialLoadSize = 20)
        )
    }
}