package com.jlndev.movies.ui.views.movie_popular_screen.domain.repository

import androidx.paging.PagingSource
import com.jlndev.movies.core.domain.model.Movie

interface MoviePopularRepository {
    fun getPopularMovies() : PagingSource<Int, Movie>
}