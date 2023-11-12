package com.jlndev.movies.ui.views.movie_detail_screen.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.core.domain.model.MovieDetails
import com.jlndev.movies.ui.views.movie_detail_screen.domain.source.MovieDetailsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(private val remoteDataSource: MovieDetailsRemoteDataSource) : MovieDetailRepository {
    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
       return remoteDataSource.getMovieDetails(movieId)
    }

    override suspend fun getMoviesSimilar(
        movieId: Int,
        pagingConfig: PagingConfig
    ): Flow<PagingData<Movie>> {
        return Pager(
            pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getSimilarMoviesPagingSource(movieId = movieId)
            }
        ).flow
    }

}