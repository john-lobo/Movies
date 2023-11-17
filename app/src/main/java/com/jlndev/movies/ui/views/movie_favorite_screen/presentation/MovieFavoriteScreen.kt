package com.jlndev.movies.ui.views.movie_favorite_screen.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jlndev.movies.R
import com.jlndev.movies.ui.presentation.components.common.MovieAppBar
import com.jlndev.movies.ui.views.movie_favorite_screen.presentation.components.MovieFavoriteContent
import com.jlndev.movies.ui.views.movie_favorite_screen.presentation.state.MovieFavoriteState

@Composable
fun MovieFavoriteScreen(
    uiState: MovieFavoriteState,
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
            movies = uiState.movies,
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
        uiState = MovieFavoriteState(),
        navigateToDetailScreen = {}
    )
}