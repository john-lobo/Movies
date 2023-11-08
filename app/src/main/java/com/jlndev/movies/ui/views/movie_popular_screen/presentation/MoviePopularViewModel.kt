package com.jlndev.movies.ui.views.movie_popular_screen.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jlndev.movies.ui.views.movie_popular_screen.domain.usecase.GetPopularMoviesUseCase
import com.jlndev.movies.ui.views.movie_popular_screen.presentation.state.MoviePopularState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviePopularViewModel @Inject constructor(
    getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MoviePopularState())
        private set

    init {
        val movies = getPopularMoviesUseCase.invoke()
            .cachedIn(viewModelScope)

        uiState = uiState.copy(movies = movies)
    }

//    private val _uiState = MutableStateFlow<Flow<PagingData<Movie>>>(emptyFlow())
//    var uiState: StateFlow<Flow<PagingData<Movie>>> = _uiState.asStateFlow()
//
//    init {
//        val movies = getPopularMoviesUseCase.invoke()
//            .cachedIn(viewModelScope)
//
//        _uiState.value = movies
//    }
}