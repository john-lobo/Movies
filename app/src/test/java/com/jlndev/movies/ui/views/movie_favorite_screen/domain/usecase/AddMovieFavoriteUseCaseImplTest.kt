package com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.jlndev.movies.TestDispatcherRule
import com.jlndev.movies.core.domain.model.MovieFactory
import com.jlndev.movies.core.util.ResultData
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.repository.MovieFavoriteRepository
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
class AddMovieFavoriteUseCaseImplTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val movie = MovieFactory().create(MovieFactory.Poster.Avengers)

    private val addMovieFavoriteUseCase by lazy {
        AddMovieFavoriteUseCaseImpl(movieFavoriteRepository)
    }

    @Test
    fun  `should return Success from Result when the repository returns success equal to unit`() = runTest {
        //Given
        whenever(movieFavoriteRepository.insert(movie)).thenReturn(Unit)

        //When
        val result = addMovieFavoriteUseCase.invoke(AddMovieFavoriteUseCase.Params(movie)).first()

        assertThat(result).isEqualTo(ResultData.Success(Unit))
    }

    @Test
    fun `must return Failure from ResultData whrn he repository throws an exception`() = runTest {

        //Given
        val exception = RuntimeException()
        whenever(movieFavoriteRepository.insert(movie))
            .thenThrow(exception)

        //When
        val result = addMovieFavoriteUseCase.invoke(AddMovieFavoriteUseCase.Params(movie)).first()

        //Then
        assertThat(result).isEqualTo(ResultData.Error(exception))
    }

}