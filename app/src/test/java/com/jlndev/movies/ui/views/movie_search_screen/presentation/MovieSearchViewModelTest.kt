package com.jlndev.movies.ui.views.movie_search_screen.presentation

import androidx.paging.PagingData
import com.google.common.truth.Truth.assertThat
import com.jlndev.movies.TestDispatcherRule
import com.jlndev.movies.core.domain.model.MovieSearchFactory
import com.jlndev.movies.ui.views.movie_search_screen.domain.usecase.GetMovieSearchUseCase
import com.nhaarman.mockitokotlin2.any
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
class MovieSearchViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getMovieSearchUseCase: GetMovieSearchUseCase

    private val viewModel by lazy {
        MovieSearchViewModel(getMovieSearchUseCase)
    }

    private val fakePagingDataSearchMovies = PagingData.from(
        listOf(
            MovieSearchFactory().create(MovieSearchFactory.Poster.Avengers),
            MovieSearchFactory().create(MovieSearchFactory.Poster.JohnWick)
        )
    )

    @Test
    fun `must validate paging data object values when calling movie search paging data`() = runTest {
        //Given
        whenever(getMovieSearchUseCase(any())).thenReturn(
            flowOf(fakePagingDataSearchMovies)
        )

        //When
        viewModel.fetch("")
        val result = viewModel.uiState.movies.first()

        //Then
        assertThat(result).isNotNull()
    }

    @Test(expected = RuntimeException::class)
    fun `must throw an exception when the calling to the use case return an exception`() = runTest {
        //Given
        whenever(getMovieSearchUseCase.invoke(any()))
            .thenThrow(RuntimeException())

        //When
        viewModel.fetch("")
    }
}