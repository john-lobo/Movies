package com.jlndev.movies.ui.views.movie_popular_screen.domain.source

import com.jlndev.movies.core.domain.model.MoviePaging
import com.jlndev.movies.core.pagingsource.MoviePagingSource

interface MoviePopularRemoteDataSource {
    suspend fun getPopularMovies(page: Int) : MoviePaging

    fun getPopularMoviesPagingSource() : MoviePagingSource
}