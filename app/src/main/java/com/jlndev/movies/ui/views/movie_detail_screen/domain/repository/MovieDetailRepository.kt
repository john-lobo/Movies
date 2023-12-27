package com.jlndev.movies.ui.views.movie_detail_screen.domain.repository

import androidx.paging.PagingSource
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.core.domain.model.MovieDetails

interface MovieDetailRepository {
    suspend fun getMovieDetails(movieId: Int) : MovieDetails
    fun getMoviesSimilar(movieId: Int): PagingSource<Int, Movie>
}