package com.jlndev.movies.core.util.ext

import com.jlndev.movies.core.data.local.entity.MovieEntity
import com.jlndev.movies.core.data.remote.model.MovieResult
import com.jlndev.movies.core.data.remote.model.SearchResult
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.core.domain.model.MovieDetails
import com.jlndev.movies.core.domain.model.MovieSearch

fun List<MovieResult>.toMovies() = map {
    Movie(
        id = it.id,
        title = it.title,
        voteAverage = it.voteAverage,
        imageUrl = it.posterPath?.toImageUrl() ?: ""
    )
}

fun List<SearchResult>.toMoviesSearch() = map {
    MovieSearch(
        id = it.id,
        voteAverage = it.voteAverage,
        imageUrl = it.posterPath?.toImageUrl() ?: ""
    )
}

fun MovieDetails.toMovie() : Movie {
    return Movie(
        id = id,
        title = title,
        voteAverage = voteAverage,
        imageUrl = backdropPathUrl.toString()
    )
}

fun Movie.toMovieEntity() : MovieEntity {
    return MovieEntity(
        movieId = id,
        imageUrl = imageUrl,
        title = title
    )
}

fun List<MovieEntity>.toMoviesEntity() = map {
    Movie(
        id = it.movieId,
        title = it.title,
        imageUrl = it.imageUrl
    )
}