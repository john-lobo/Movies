package com.jlndev.movies.movie_popular_feature.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.jlndev.movies.core.util.UtilFunctions
import com.jlndev.movies.movie_popular_feature.presentation.components.MovieAdapter
import com.jlndev.movies.movie_popular_feature.presentation.state.MoviePopularState
import com.jlndev.movies.ui.theme.black
import com.jlndev.movies.ui.theme.white

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
            TopAppBar(
                title = {
                    Text(
                        text = "Popular movies",
                        color = white
                    )
                },
                backgroundColor = black
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