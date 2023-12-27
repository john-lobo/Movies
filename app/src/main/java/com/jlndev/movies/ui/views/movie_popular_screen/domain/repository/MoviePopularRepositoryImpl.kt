package com.jlndev.movies.ui.views.movie_popular_screen.domain.repository

import androidx.paging.PagingSource
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.core.pagingsource.MoviePagingSource
import com.jlndev.movies.ui.views.movie_popular_screen.domain.source.MoviePopularRemoteDataSource
import javax.inject.Inject

class MoviePopularRepositoryImpl @Inject constructor(
    private val remoteDataSource: MoviePopularRemoteDataSource
) : MoviePopularRepository {

    override fun getPopularMovies(): PagingSource<Int, Movie> {
        return MoviePagingSource(remoteDataSource)
    }
}