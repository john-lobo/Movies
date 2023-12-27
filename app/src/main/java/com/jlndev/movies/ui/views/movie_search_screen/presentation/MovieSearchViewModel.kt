package com.jlndev.movies.ui.views.movie_search_screen.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.jlndev.movies.ui.views.movie_search_screen.domain.usecase.GetMovieSearchUseCase
import com.jlndev.movies.ui.views.movie_search_screen.presentation.state.MovieSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val getMovieSearchUseCase: GetMovieSearchUseCase
): ViewModel() {
    var uiState by mutableStateOf(MovieSearchState())
        private set

    fun fetch(query: String = "") {
        val movies = getMovieSearchUseCase.invoke(
            GetMovieSearchUseCase.Params(
                query = query,
                pagingConfig = pagingConfig()
            )
        ).cachedIn(viewModelScope)
        uiState = uiState.copy(movies = movies)
    }

    fun event(query: String) {
        uiState = uiState.copy(query = query)
    }

    private fun pagingConfig(): PagingConfig {
        return PagingConfig(
            pageSize = 20,
            initialLoadSize = 20
        )
    }
}