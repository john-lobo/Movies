package com.jlndev.movies.ui.views.movie_popular_screen.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.jlndev.movies.R
import com.jlndev.movies.core.util.UtilFunctions
import com.jlndev.movies.ui.presentation.components.common.MovieAppBar
import com.jlndev.movies.ui.views.movie_popular_screen.presentation.components.MovieAdapter
import com.jlndev.movies.ui.views.movie_popular_screen.presentation.state.MoviePopularState

@Composable
fun MoviePopularScreen(
    modifier: Modifier = Modifier,
    uiState: MoviePopularState,
    navigationToDetailMovie: (Int) -> Unit,

    ) {
    val movies = uiState.movies.collectAsLazyPagingItems()
    Scaffold(
        modifier = modifier,
        topBar = {
            MovieAppBar(
                title = R.string.popular_movies
            )
        },
        content = { paddingValues ->
            MovieAdapter(
                pagingMovies = movies,
                paddingValue =  paddingValues,
                onClick =  {
                    UtilFunctions.logError("MOVIE_ID", it.toString())
                    navigationToDetailMovie(it)
                }
            )
        }
    ) 
}