package com.jlndev.movies.ui.views.movie_favorite_screen.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jlndev.movies.R
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.ui.presentation.components.common.MovieAppBar
import com.jlndev.movies.ui.views.movie_favorite_screen.presentation.components.MovieFavoriteContent

@Composable
fun MovieFavoriteScreen(
    movies: List<Movie>,
    navigateToDetailScreen: (Int) -> Unit
) {
    Scaffold (
        topBar = {
            MovieAppBar(
                title = R.string.favorite_movies
            )
        }
    ){
        MovieFavoriteContent(
            paddingValues = it,
            movies = movies,
            onClick =  { movieId ->
                navigateToDetailScreen(movieId)
            }
        )
    }
}

@Preview
@Composable
fun MovieFavoriteScreenPreview() {
    MovieFavoriteScreen(
        emptyList(),
        navigateToDetailScreen = {}
    )
}