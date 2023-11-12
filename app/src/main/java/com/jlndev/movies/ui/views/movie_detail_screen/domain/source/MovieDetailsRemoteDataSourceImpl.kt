package com.jlndev.movies.ui.views.movie_detail_screen.domain.source

import com.jlndev.movies.core.data.remote.MovieService
import com.jlndev.movies.core.data.remote.response.MovieResponse
import com.jlndev.movies.core.domain.model.MovieDetails
import com.jlndev.movies.core.pagingsource.MovieSimilarPagingSource
import com.jlndev.movies.core.util.ext.toImageUrl
import javax.inject.Inject

class MovieDetailsRemoteDataSourceImpl @Inject constructor(private val service: MovieService) : MovieDetailsRemoteDataSource {
    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val response = service.getMovie(movieId)
        return MovieDetails(
            id = response.id,
            title = response.title,
            genres = response.genres.map { it.name },
            overview = response.overview,
            releaseDate = response.releaseDate,
            backdropPathUrl = response.backdropPath.toImageUrl(),
            voteAverage = response.voteAverage,
            duration = response.runtime,
            voteCount = response.voteCount

        )
    }

    override suspend fun getMoviesSimilar(page: Int, movieId: Int): MovieResponse {
        return service.getMoviesSimilar(
            page = page,
            movieId = movieId
        )
    }

    override fun getSimilarMoviesPagingSource(movieId: Int): MovieSimilarPagingSource {
        return MovieSimilarPagingSource(this, movieId)
    }
}