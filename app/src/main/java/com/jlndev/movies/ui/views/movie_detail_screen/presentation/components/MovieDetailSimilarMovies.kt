package com.jlndev.movies.ui.views.movie_detail_screen.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.ui.presentation.components.common.ErrorScreen
import com.jlndev.movies.ui.presentation.components.common.LoadingView
import com.jlndev.movies.ui.views.movie_popular_screen.presentation.components.MovieItem


@Composable
fun MovieDetailSimilarMovies(
    pagingMovieSimilar: LazyPagingItems<Movie>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.Center
    ) {
        items(pagingMovieSimilar.itemCount) { index ->
            val movie = pagingMovieSimilar[index]
            movie?.let {
                MovieItem(
                    id = it.id,
                    voteAverage = it.voteAverage,
                    imageUrl = it.imageUrl,
                    onClick = {}
                )
            }
        }

        pagingMovieSimilar.apply {
            when {
                loadState.append is LoadState.Loading || loadState.refresh is LoadState.Loading -> {
                    item (
                        span = { GridItemSpan(maxLineSpan) }
                    ) {
                        LoadingView()
                    }
                }

                loadState.append is LoadState.Error || loadState.refresh is LoadState.Error -> {
                    val error = pagingMovieSimilar.loadState.refresh as LoadState.Error
                    item (
                        span = { GridItemSpan(maxLineSpan) }
                    ) {
                        ErrorScreen(message = error.error.message.toString()) {
                            retry()
                        }
                    }
                }
            }
        }
    }
}

