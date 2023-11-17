package com.jlndev.movies.ui.views.movie_detail_screen.presentation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.jlndev.movies.R
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.ui.theme.black
import com.jlndev.movies.ui.theme.white
import com.jlndev.movies.ui.views.movie_detail_screen.presentation.components.MovieDetailContent
import com.jlndev.movies.ui.views.movie_detail_screen.presentation.state.MovieDetailState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailScreen(
    uiState: MovieDetailState,
    onAddFavorite: (Movie) -> Unit,
) {

    val pagingMoviesSimilar = uiState.results.collectAsLazyPagingItems()

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.detail_movie),
                        color = white
                    )
                },
                backgroundColor = black
            )
        }
    ) { paddingValues ->
        MovieDetailContent(
            movieDetails = uiState.movieDetails,
            pagingMoviesSimilar = pagingMoviesSimilar,
            isLoading = uiState.isLoading,
            isError = uiState.error,
            iconColor = uiState.iconColor,
            onAddFavorite = { movie ->
                onAddFavorite(movie)
            }
        )
    }
}