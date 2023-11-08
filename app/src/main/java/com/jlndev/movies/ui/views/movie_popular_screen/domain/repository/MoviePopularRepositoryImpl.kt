package com.jlndev.movies.ui.views.movie_popular_screen.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.ui.views.movie_popular_screen.domain.source.MoviePopularRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviePopularRepositoryImpl @Inject constructor(
    private val remoteDataSource: MoviePopularRemoteDataSource
) : MoviePopularRepository {

    override fun getPopularMovies(pagingConfig: PagingConfig): Flow<PagingData<Movie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getPopularMoviesPagingSource()
            }
        ).flow
    }
}