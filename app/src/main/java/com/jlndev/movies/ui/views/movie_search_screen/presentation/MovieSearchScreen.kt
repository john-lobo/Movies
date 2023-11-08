package com.jlndev.movies.ui.views.movie_search_screen.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.jlndev.movies.ui.theme.black
import com.jlndev.movies.ui.theme.white
import com.jlndev.movies.ui.views.movie_search_screen.presentation.components.SearchContent
import com.jlndev.movies.ui.views.movie_search_screen.presentation.state.MovieSearchState

@Composable
fun MovieSearchScreen(
    uiState: MovieSearchState,
    onEvent: (String) -> Unit,
    onFetch: (String) -> Unit,
    navigateToDetailMovie: (Int) -> Unit
) {

    val movies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pesquisar Filmes",
                        color = white
                    )
                },
                backgroundColor = black
            )
        },
        content = { paddingValues ->
            SearchContent(
                paddingValues = paddingValues,
                pagingMovies = movies,
                query = uiState.query,
                onSearch = { onFetch(it) },
                onEvent = { onEvent(it) },
                onDetail = { navigateToDetailMovie(it) }
            )
        }
    )
}