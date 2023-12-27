package com.jlndev.movies.ui.views.movie_detail_screen.presentation

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import com.google.common.truth.Truth.assertThat
import com.jlndev.movies.TestDispatcherRule
import com.jlndev.movies.core.domain.model.MovieDetailFactory
import com.jlndev.movies.core.domain.model.MovieFactory
import com.jlndev.movies.core.util.ResultData
import com.jlndev.movies.core.util.ext.toMovie
import com.jlndev.movies.ui.views.movie_detail_screen.domain.usecase.GetMovieDetailUseCase
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase.AddMovieFavoriteUseCase
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase.DeleteMovieFavoriteUseCase
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase.IsMovieFavoriteUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    @Mock
    lateinit var addMovieFavoriteUseCase: AddMovieFavoriteUseCase

    @Mock
    lateinit var deleteMovieFavoriteUseCase: DeleteMovieFavoriteUseCase

    @Mock
    lateinit var isMovieFavoriteUseCase: IsMovieFavoriteUseCase

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    private val movieDetailFactory = MovieDetailFactory().create(MovieDetailFactory.Poster.Avengers)

    private val fakePagingDataMovies = PagingData.from(
        listOf(
            MovieFactory().create(MovieFactory.Poster.Avengers),
            MovieFactory().create(MovieFactory.Poster.JohnWick)
        )
    )

    private val viewModel by lazy {
        MovieDetailViewModel(
            getMovieDetailUseCase = getMovieDetailUseCase,
            addMovieFavoriteUseCase = addMovieFavoriteUseCase,
            deleteMovieFavoriteUseCase = deleteMovieFavoriteUseCase,
            isMovieFavoriteUseCase = isMovieFavoriteUseCase,
            savedStateHandle = savedStateHandle.apply {
                whenever(savedStateHandle.get<Int>("movieId")).thenReturn(movieDetailFactory.id)
            }
        )
    }

    @Test
    fun `must notify uiState with Success when get movies similar and movie details returns success`() = runTest {

        //Give
        whenever(getMovieDetailUseCase.invoke(any()))
            .thenReturn(ResultData.Success(flowOf(fakePagingDataMovies) to movieDetailFactory))

        whenever(isMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(true)))

        val argumentCaptor = argumentCaptor<GetMovieDetailUseCase.Params>()
        val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

        //When
        viewModel.uiState.isLoading

        //Then
        verify(getMovieDetailUseCase).invoke(argumentCaptor.capture())
        assertThat(movieDetailFactory.id).isEqualTo(argumentCaptor.firstValue.movieId)

        verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
        assertThat(movieDetailFactory.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

        val movieDetails = viewModel.uiState.movieDetails
        val results = viewModel.uiState.results
        assertThat(movieDetails).isNotNull()
        assertThat(results).isNotNull()
    }

    @Test
    fun `must notify uiState with Failure when get movies details and returns exception`() = runTest {
        //Given
        val exception = Exception("Um erro ocorreu!")
        whenever(getMovieDetailUseCase.invoke(any()))
            .thenReturn(ResultData.Error(exception))

        whenever(isMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Error(exception)))

        //When
        viewModel.uiState.isLoading

        //Then
        val error = viewModel.uiState.error
        assertThat(exception.message).isEqualTo(error)
    }

    @Test
    fun `must call delete favorite and notify of uiState with filled favorite icon when current icon is checked`() = runTest {
        //Given
        val movie = movieDetailFactory.toMovie()
        whenever(deleteMovieFavoriteUseCase.invoke(any()))
                .thenReturn(flowOf(ResultData.Success(Unit)))

        whenever(isMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(true)))

        val deleteArgumentCaptor = argumentCaptor<DeleteMovieFavoriteUseCase.Params>()
        val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

        //When
        viewModel.onAddFavorite(movie)

        //Then
        verify(deleteMovieFavoriteUseCase).invoke(deleteArgumentCaptor.capture())
        assertThat(movie).isEqualTo(deleteArgumentCaptor.firstValue.movie)

        verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
        assertThat(movieDetailFactory.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

        val iconColor = viewModel.uiState.iconColor
        assertThat(Color.White).isEqualTo(iconColor)
    }

    @Test
    fun `must notify uiState with filled favorite icon when current icon is unchecked`() = runTest {
        //Given
        val movie = movieDetailFactory.toMovie()
        whenever(addMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(Unit)))

        whenever(isMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(false)))


        val addArgumentCaptor = argumentCaptor<AddMovieFavoriteUseCase.Params>()
        val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

        //When
        viewModel.onAddFavorite(movie)

        //Then
        verify(addMovieFavoriteUseCase).invoke(addArgumentCaptor.capture())
        assertThat(movie).isEqualTo(addArgumentCaptor.firstValue.movie)

        verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
        assertThat(movieDetailFactory.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

        val iconColor = viewModel.uiState.iconColor
        assertThat(Color.Red).isEqualTo(iconColor)
    }

    @Test
    fun `must notify uiState with bookmark icon filled in if bookmark check returns true`() = runTest {
        //Given
        whenever(isMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(true)))

        whenever(getMovieDetailUseCase.invoke(any()))
            .thenReturn(ResultData.Success(flowOf(fakePagingDataMovies) to movieDetailFactory))

        val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

        //When
        viewModel.uiState.isLoading

        //Then
        verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
        assertThat(movieDetailFactory.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

        val iconColor = viewModel.uiState.iconColor
        assertThat(Color.Red).isEqualTo(iconColor)
    }

    @Test
    fun `must notigy uiState with bookmark icon filled in if bookmark check returns false`() = runTest {
        //Given
        whenever(isMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(false)))

        whenever(getMovieDetailUseCase.invoke(any()))
            .thenReturn(ResultData.Success(flowOf(fakePagingDataMovies) to movieDetailFactory))

        val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

        //When
        viewModel.uiState.isLoading

        //Then
        verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
        assertThat(movieDetailFactory.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

        val iconColor = viewModel.uiState.iconColor
        assertThat(Color.White).isEqualTo(iconColor)
    }
}