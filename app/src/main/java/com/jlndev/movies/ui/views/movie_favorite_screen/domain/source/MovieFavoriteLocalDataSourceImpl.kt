package com.jlndev.movies.ui.views.movie_favorite_screen.domain.source

import com.jlndev.movies.core.data.local.dao.MovieDao
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.core.util.ext.toMovieEntity
import com.jlndev.movies.core.util.ext.toMoviesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieFavoriteLocalDataSourceImpl @Inject constructor(
    private val dao: MovieDao
) : MovieFavoriteLocalDataSource {
    override fun getMovies(): Flow<List<Movie>> {
        return dao.getMovies().map { it.toMoviesEntity() }
    }

    override suspend fun insert(movie: Movie) {
        dao.insertMovies(movie.toMovieEntity())
    }

    override suspend fun delete(movie: Movie) {
        return dao.deleteMovie(movie.toMovieEntity())
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return dao.isFavorite(movieId) != null
    }

}