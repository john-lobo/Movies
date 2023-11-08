package com.jlndev.movies.ui.views.movie_search_screen.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jlndev.movies.core.domain.model.MovieSearch
import com.jlndev.movies.ui.views.movie_search_screen.domain.source.MovieSearchRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieSearchRepositoryImpl @Inject constructor(private val remoteDataSource: MovieSearchRemoteDataSource): MovieSearchRepository {
    override fun getPopularMovies(
        query: String,
        pagingConfig: PagingConfig
    ): Flow<PagingData<MovieSearch>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { remoteDataSource.getSearchMoviePagingSource(query) }
        ).flow
    }
}