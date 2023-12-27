package com.jlndev.movies.ui.views.movie_favorite_screen.presentation.state

import com.google.common.truth.Truth.assertThat
import com.jlndev.movies.TestDispatcherRule
import com.jlndev.movies.core.domain.model.MovieFactory
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase.GetMoviesFavoriteUseCase
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner



@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieFavoriteViewModelTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getMoviesFavoriteUseCase: GetMoviesFavoriteUseCase

    private val viewModel by lazy {
        MovieFavoriteViewModel(getMoviesFavoriteUseCase)
    }

    private val fakeMoviesFavorites = listOf(
        MovieFactory().create(MovieFactory.Poster.Avengers),
        MovieFactory().create(MovieFactory.Poster.JohnWick)
    )

    @Test
    fun `must validate the data object values when list of favorites`() = runTest {
        //Given
        whenever(getMoviesFavoriteUseCase.invoke()).thenReturn(
            flowOf(fakeMoviesFavorites)
        )

        //When
        val result = viewModel.uiState.movies.first()

        //Then
        assertThat(result).isNotEmpty()
        assertThat(result).contains(fakeMoviesFavorites[0])
    }
}

