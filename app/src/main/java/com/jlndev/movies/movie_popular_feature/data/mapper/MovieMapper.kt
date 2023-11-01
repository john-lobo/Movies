package com.jlndev.movies.movie_popular_feature.data.mapper

import com.jlndev.movies.core.data.remote.model.MovieResult
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.core.util.toImageUrl

fun List<MovieResult>.toMovies() = map {
    Movie(
        id = it.id,
        title = it.title,
        voteAverage = it.voteAverage,
        imageUrl = it.posterPath?.toImageUrl() ?: ""
    )
}