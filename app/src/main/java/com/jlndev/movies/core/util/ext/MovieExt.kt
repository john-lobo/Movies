package com.jlndev.movies.core.util.ext

import com.jlndev.movies.core.data.remote.model.MovieResult
import com.jlndev.movies.core.domain.model.Movie

fun List<MovieResult>.toMovies() = map {
    Movie(
        id = it.id,
        title = it.title,
        voteAverage = it.voteAverage,
        imageUrl = it.posterPath?.toImageUrl() ?: ""
    )
}