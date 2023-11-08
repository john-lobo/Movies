package com.jlndev.movies.ui.views.movie_popular_screen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.ui.presentation.components.common.ErrorScreen
import com.jlndev.movies.ui.presentation.components.common.LoadingView

@Composable
fun MovieAdapter(
    modifier: Modifier = Modifier,
    pagingMovies: LazyPagingItems<Movie>,
    paddingValue: PaddingValues,
    onClick: (movieId: Int) -> Unit
) {
    Box(modifier = modifier.background(Color.Black)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = paddingValue,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            items(pagingMovies.itemCount) { index ->
                val movie = pagingMovies[index]
                movie?.let {
                    MovieItem(
                        id = it.id,
                        voteAverage = it.voteAverage,
                        imageUrl = it.imageUrl,
                        onClick = { movieId ->
                            onClick(movieId)
                        }
                    )
                }
            }

            pagingMovies.apply {
                when {
                    loadState.append is LoadState.Loading || loadState.refresh is LoadState.Loading -> {
                        item (
                            span = { GridItemSpan(maxLineSpan) }
                        ) {
                            LoadingView()
                        }
                    }

                    loadState.append is LoadState.Error || loadState.refresh is LoadState.Error -> {
                        item (
                            span = { GridItemSpan(maxLineSpan) }
                        ) {
                            ErrorScreen(message = "Erro ao carrega dados") {
                                retry()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieContentPreview() {
}