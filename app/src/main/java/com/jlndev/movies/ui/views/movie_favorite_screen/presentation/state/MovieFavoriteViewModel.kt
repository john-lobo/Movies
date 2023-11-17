package com.jlndev.movies.ui.views.movie_favorite_screen.presentation.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase.GetMoviesFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieFavoriteViewModel @Inject constructor(
    private val getMoviesFavoriteUseCase: GetMoviesFavoriteUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MovieFavoriteState())
        private set

    init {
        fetch()
    }

    private fun fetch() {
        viewModelScope.launch {
            getMoviesFavoriteUseCase.invoke().collectLatest { movies ->
                uiState = uiState.copy(movies = movies)
            }
        }
    }
}