package com.jlndev.movies.ui.views.movie_search_screen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.jlndev.movies.core.domain.model.MovieSearch
import com.jlndev.movies.ui.presentation.components.common.ErrorScreen
import com.jlndev.movies.ui.presentation.components.common.LoadingView
import com.jlndev.movies.ui.theme.black
import com.jlndev.movies.ui.views.movie_popular_screen.presentation.components.MovieItem

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    pagingMovies: LazyPagingItems<MovieSearch>,
    query: String,
    onSearch: (String) -> Unit,
    onEvent: (String) -> Unit,
    onDetail: (movieId: Int) -> Unit
) {

    var isLoading by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(black),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        SearchComponent(
            query = query,
            onSearch = {
                isLoading = true
                onSearch(it)
            },
            onQueryChangeEvent = {
                onEvent(it)
            },
            modifier = Modifier.padding(all = 8.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = paddingValues,
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
                            onDetail(movieId)
                        }
                    )
                }
                isLoading = false
            }

            pagingMovies.apply {
                when {
                    isLoading -> {
                        item (
                            span = { GridItemSpan(maxLineSpan) }
                        ) {
                            LoadingView()
                        }
                    }

                    loadState.append is LoadState.Error || loadState.refresh is LoadState.Error -> {
                        isLoading = false
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