package com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.jlndev.movies.TestDispatcherRule
import com.jlndev.movies.core.domain.model.MovieFactory
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.repository.MovieFavoriteRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMoviesFavoriteUseCaseImplTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val getMoviesFavoriteUseCase by lazy {
        GetMoviesFavoriteUseCaseImpl(movieFavoriteRepository)
    }

    private val movies = listOf(
        MovieFactory().create(MovieFactory.Poster.Avengers),
        MovieFactory().create(MovieFactory.Poster.JohnWick)
    )

    @Test
    fun `should return Success from ResultData when the repository returns a list of movies`() = runTest {
        //Given
        whenever(movieFavoriteRepository.getMovies())
            .thenReturn(flowOf(movies))

        //When
        val result = getMoviesFavoriteUseCase.invoke().first()

        //Then
        assertThat(result).isEqualTo(movies)
    }

    @Test
    fun `should emit an empty stream when exception is thrown when calling the invoke method`() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(movieFavoriteRepository.getMovies())
            .thenThrow(exception)

        //When
        val result = getMoviesFavoriteUseCase.invoke().toList()

        //Then
        verify(movieFavoriteRepository).getMovies()
        assertThat(result).isEmpty()
    }

}