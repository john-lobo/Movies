package com.jlndev.movies.ui.views.movie_favorite_screen.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jlndev.movies.R
import com.jlndev.movies.ui.theme.black
import com.jlndev.movies.ui.theme.white
import com.jlndev.movies.ui.views.movie_favorite_screen.presentation.components.MovieFavoriteContent
import com.jlndev.movies.ui.views.movie_favorite_screen.presentation.state.MovieFavoriteState

@Composable
fun MovieFavoriteScreen(
    uiState: MovieFavoriteState,
    navigateToDetailScreen: (Int) -> Unit
) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.favorite_movies),
                        color = white
                    )
                },
                backgroundColor = black
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