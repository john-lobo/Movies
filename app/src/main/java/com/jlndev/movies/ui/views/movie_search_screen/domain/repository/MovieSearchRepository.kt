package com.jlndev.movies.ui.views.movie_search_screen.domain.repository

import androidx.paging.PagingSource
import com.jlndev.movies.core.domain.model.MovieSearch

interface MovieSearchRepository {
    fun getPopularMovies(query: String) : PagingSource<Int, MovieSearch>
}