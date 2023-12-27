package com.jlndev.movies.ui.views.movie_detail_screen.domain.repository

import androidx.paging.PagingSource
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.core.domain.model.MovieDetails
import com.jlndev.movies.core.pagingsource.MovieSimilarPagingSource
import com.jlndev.movies.ui.views.movie_detail_screen.domain.source.MovieDetailsRemoteDataSource
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(private val remoteDataSource: MovieDetailsRemoteDataSource) :
    MovieDetailRepository {
    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return remoteDataSource.getMovieDetails(movieId)
    }

    override fun getMoviesSimilar(
        movieId: Int,
    ): PagingSource<Int, Movie> {
        return MovieSimilarPagingSource(movieId = movieId, remoteDataSource = remoteDataSource)
    }

}