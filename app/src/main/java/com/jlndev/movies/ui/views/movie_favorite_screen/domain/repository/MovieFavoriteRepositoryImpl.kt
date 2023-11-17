package com.jlndev.movies.ui.views.movie_favorite_screen.domain.repository

import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.source.MovieFavoriteLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieFavoriteRepositoryImpl @Inject constructor(
    private val dataSource: MovieFavoriteLocalDataSource
) : MovieFavoriteRepository {
    override fun getMovies(): Flow<List<Movie>> {
        return dataSource.getMovies()
    }

    override suspend fun insert(movie: Movie) {
        dataSource.insert(movie)
    }

    override suspend fun delete(movie: Movie) {
        dataSource.delete(movie)
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return dataSource.isFavorite(movieId)
    }
}