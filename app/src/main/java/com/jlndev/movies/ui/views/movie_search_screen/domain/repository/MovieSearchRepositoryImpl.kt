package com.jlndev.movies.ui.views.movie_search_screen.domain.repository

import androidx.paging.PagingSource
import com.jlndev.movies.core.domain.model.MovieSearch
import com.jlndev.movies.core.pagingsource.MovieSearchPagingSource
import com.jlndev.movies.ui.views.movie_search_screen.domain.source.MovieSearchRemoteDataSource
import javax.inject.Inject

class MovieSearchRepositoryImpl @Inject constructor(private val remoteDataSource: MovieSearchRemoteDataSource): MovieSearchRepository {
    override fun getPopularMovies(query: String): PagingSource<Int, MovieSearch> {
        return MovieSearchPagingSource(query, remoteDataSource)
    }
}