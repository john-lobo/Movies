package com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase

import com.google.common.truth.Truth
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
class DeleteMovieFavoriteUseCaseImplTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val movie = MovieFactory().create(MovieFactory.Poster.Avengers)

    private val deleteMovieFavoriteUseCase by lazy {
        DeleteMovieFavoriteUseCaseImpl(movieFavoriteRepository)
    }

    @Test
    fun  `should return Success from Result when the repository returns success equal to unit`() = runTest {
        //Given
        whenever(movieFavoriteRepository.delete(movie))
            .thenReturn(Unit)

        //When
        val result = deleteMovieFavoriteUseCase.invoke(DeleteMovieFavoriteUseCase.Params(movie)).first()

        //Then
        Truth.assertThat(result).isEqualTo(ResultData.Success(Unit))
    }

    @Test
    fun `must return Failure from ResultData whrn he repository throws an exception`() = runTest {

        //Given
        val exception = RuntimeException()
        whenever(movieFavoriteRepository.delete(movie))
            .thenThrow(exception)

        //When
        val result = deleteMovieFavoriteUseCase.invoke(DeleteMovieFavoriteUseCase.Params(movie)).first()

        //Then
        Truth.assertThat(result).isEqualTo(ResultData.Error(exception))
    }

}