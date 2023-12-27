package com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.jlndev.movies.TestDispatcherRule
import com.jlndev.movies.core.domain.model.MovieFactory
import com.jlndev.movies.core.util.ResultData
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.repository.MovieFavoriteRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class IsMovieFavoriteUseCaseImplTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val movie = MovieFactory().create(MovieFactory.Poster.Avengers)

    private val isMovieFavoriteUseCase by lazy {
        IsMovieFavoriteUseCaseImpl(movieFavoriteRepository)
    }

    @Test
    fun `must return Success from ResultData when the repository returns success with the value equal to true`() = runTest {
        //Given

        whenever(movieFavoriteRepository.isFavorite(any()))
            .thenReturn(true)


        //When
        val result = isMovieFavoriteUseCase.invoke(
            IsMovieFavoriteUseCase.Params(movie.id)
        ).first()

        //Then
        assertThat(result).isEqualTo(ResultData.Success(true))
    }

    @Test
    fun `must return Success from ResultData when the repository returns success with the value equal to false`() = runTest {
        //Given

        whenever(movieFavoriteRepository.isFavorite(any()))
            .thenReturn(false)


        //When
        val result = isMovieFavoriteUseCase.invoke(
            IsMovieFavoriteUseCase.Params(movie.id)
        ).first()

        //Then
        assertThat(result).isEqualTo(ResultData.Success(false))
    }

    @Test
    fun `must return Error from ResultData when the repository throws an exception`() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(movieFavoriteRepository.isFavorite(any()))
            .thenThrow(exception)


        //When
        val result = isMovieFavoriteUseCase.invoke(
            IsMovieFavoriteUseCase.Params(movie.id)
        ).first()

        //Then
        assertThat(result).isEqualTo(ResultData.Error(exception))
    }

}