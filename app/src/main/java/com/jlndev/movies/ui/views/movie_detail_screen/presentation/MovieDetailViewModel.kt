package com.jlndev.movies.ui.views.movie_detail_screen.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.core.util.Constants
import com.jlndev.movies.core.util.ResultData
import com.jlndev.movies.core.util.UtilFunctions
import com.jlndev.movies.ui.views.movie_detail_screen.domain.usecase.GetMovieDetailUseCase
import com.jlndev.movies.ui.views.movie_detail_screen.presentation.state.MovieDetailState
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase.AddMovieFavoriteUseCase
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase.DeleteMovieFavoriteUseCase
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase.IsMovieFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val addMovieFavoriteUseCase: AddMovieFavoriteUseCase,
    private val deleteMovieFavoriteUseCase: DeleteMovieFavoriteUseCase,
    private val isMovieFavoriteUseCase: IsMovieFavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var uiState by mutableStateOf(MovieDetailState())
        private set

    private val movieId = savedStateHandle.get<Int>(key = Constants.MOVIE_DETAIL_ARGUMENT_KEY)

    init {
        movieId?.let { safeMovieId ->
            checkedFavorite(MovieDetailEvent.CheckedFavorite(safeMovieId))
            getMovieDetail(MovieDetailEvent.GetMovieDetail(safeMovieId))
        }
    }

    private fun getMovieDetail(getMovieDetail: MovieDetailEvent.GetMovieDetail) {
        event(getMovieDetail)
    }

    private fun checkedFavorite(checkedFavorite: MovieDetailEvent.CheckedFavorite) {
        event(checkedFavorite)
    }

    fun onAddFavorite(movie: Movie) {
        if (uiState.iconColor == Color.White) {
            event(MovieDetailEvent.AddFavorite(movie))
        } else {
            event(MovieDetailEvent.RemoveFavorite(movie))
        }
    }

    private fun event(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.AddFavorite -> {
                viewModelScope.launch {
                    addMovieFavoriteUseCase.invoke(
                        params = AddMovieFavoriteUseCase.Params(event.movie)
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Loading -> {}
                            is ResultData.Success -> {
                                uiState = uiState.copy(
                                    iconColor = Color.Red
                                )
                            }

                            is ResultData.Error -> {
                                UtilFunctions.logError(
                                    "DETAIL_ADD_FAVORITE_ERROR",
                                    result.throwable?.message.toString()
                                )
                            }
                        }
                    }
                }
            }

            is MovieDetailEvent.RemoveFavorite -> {
                viewModelScope.launch {
                    deleteMovieFavoriteUseCase.invoke(
                        params = DeleteMovieFavoriteUseCase.Params(event.movie)
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Loading -> {}
                            is ResultData.Success -> {
                                uiState = uiState.copy(
                                    iconColor = Color.White
                                )
                            }

                            is ResultData.Error -> {
                                UtilFunctions.logError(
                                    "DETAIL_DELETE_FAVORITE_ERROR",
                                    result.throwable?.message.toString()
                                )
                            }
                        }
                    }
                }
            }

            is MovieDetailEvent.CheckedFavorite -> {
                viewModelScope.launch {
                    isMovieFavoriteUseCase.invoke(
                        params = IsMovieFavoriteUseCase.Params(event.movieId)
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Loading -> {}
                            is ResultData.Success -> {
                                uiState = uiState.copy(
                                    iconColor = if (result.data == true) Color.Red else Color.White
                                )
                            }

                            is ResultData.Error -> {
                                UtilFunctions.logError(
                                    "DETAIL_IS_FAVORITE_ERROR",
                                    result.throwable?.message.toString()
                                )
                            }
                        }
                    }
                }
            }

            is MovieDetailEvent.GetMovieDetail -> {
                viewModelScope.launch {
                    val resultData = getMovieDetailUseCase.invoke(
                        GetMovieDetailUseCase.Params(
                            movieId = event.movieId,
                            pagingConfig = pagingConfig()
                        )
                    )
                    when (resultData) {
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

    private fun pagingConfig(): PagingConfig {
        return PagingConfig(
            pageSize = 20,
            initialLoadSize = 20
        )
    }
}