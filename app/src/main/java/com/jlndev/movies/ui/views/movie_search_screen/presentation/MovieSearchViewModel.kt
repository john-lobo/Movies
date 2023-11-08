package com.jlndev.movies.ui.views.movie_search_screen.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jlndev.movies.ui.views.movie_search_screen.domain.usecase.GetMovieSearchUse
import com.jlndev.movies.ui.views.movie_search_screen.presentation.state.MovieSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val getMovieSearchUse: GetMovieSearchUse
): ViewModel() {
    var uiState by mutableStateOf(MovieSearchState())
        private set

    fun fetch(query: String = "") {
        val movies = getMovieSearchUse.invoke(query).cachedIn(viewModelScope)
        uiState = uiState.copy(movies = movies)
    }

    fun event(query: String) {
        uiState = uiState.copy(query = query)
    }
}