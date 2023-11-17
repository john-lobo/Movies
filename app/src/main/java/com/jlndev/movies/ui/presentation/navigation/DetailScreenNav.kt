package com.jlndev.movies.ui.presentation.navigation

import com.jlndev.movies.core.util.Constants

sealed class DetailScreenNav (
    val route: String
) {
    object DetailScreen: DetailScreenNav (
        route =  "movie_detail_screen?${Constants.MOVIE_DETAIL_ARGUMENT_KEY}=" +
                "{${Constants.MOVIE_DETAIL_ARGUMENT_KEY}}"
    ) {
        fun passMovieId(movieId: Int) =
            "movie_detail_screen?${Constants.MOVIE_DETAIL_ARGUMENT_KEY}=$movieId"
    }
}