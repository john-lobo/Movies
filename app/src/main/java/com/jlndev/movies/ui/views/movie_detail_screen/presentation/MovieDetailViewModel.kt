package com.jlndev.movies.ui.views.movie_detail_screen.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlndev.movies.core.util.ResultData
import com.jlndev.movies.core.util.UtilFunctions
import com.jlndev.movies.ui.views.movie_detail_screen.domain.usecase.GetMovieDetailUseCase
import com.jlndev.movies.ui.views.movie_detail_screen.presentation.state.MovieDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
): ViewModel() {
    var uiState by mutableStateOf(MovieDetailState())
        private set

    fun getMovieDetail(getMovieDetail: MovieDetailEvent.GetMovieDetail) {
        event(getMovieDetail)
    }

    private fun event(event : MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.GetMovieDetail -> {
                viewModelScope.launch {
                    getMovieDetailUseCase.invoke(event.movieId)
                        .collect { resultData ->
                            when(resultData) {
                                is ResultData.Success -> {
                                    uiState = uiState.copy(
                                        isLoading = false,
                                        movieDetails = resultData.data?.second,
                                        results = resultData.data?.first ?: emptyFlow()
                                    )
                                }
                                is ResultData.Error -> {
                                    uiState = uiState.copy(
                                        isLoading = false,
                                        error = resultData.throwable?.message.toString()
                                    )
                                    UtilFunctions.logError(
                                        "DETAIL_ERROR",
                                        resultData.throwable?.message.toString()
                                    )
                                }
                                is ResultData.Loading -> {
                                    uiState = uiState.copy(
                                        isLoading = true
                                    )
                                }
                            }
                        }
                }
            }
        }
    }
}