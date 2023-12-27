package com.jlndev.movies.ui.views.movie_popular_screen.domain.usecase

import androidx.paging.PagingConfig
import com.google.common.truth.Truth
import com.jlndev.movies.TestDispatcherRule
import com.jlndev.movies.core.domain.model.MovieFactory
import com.jlndev.movies.core.domain.model.PagingSourceMoviesFactory
import com.jlndev.movies.ui.views.movie_popular_screen.domain.repository.MoviePopularRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetPopularMoviesUseCaseImplTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var repository: MoviePopularRepository

    private val getPopularMoviesUseCase by lazy {
        GetPopularMoviesUseCaseImpl(repository)
    }

    private val movie = MovieFactory().create(MovieFactory.Poster.Avengers)

    private val pagingSourceFake = PagingSourceMoviesFactory()
        .create(listOf(movie))

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() = runTest {
        //Given
        whenever(repository.getPopularMovies())
            .thenReturn(pagingSourceFake)

        //When
        val result = getPopularMoviesUseCase.invoke(
            GetPopularMoviesUseCase.Params(
                PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        ).first()

        //Then
        verify(repository).getPopularMovies()
        Truth.assertThat(result).isNotNull()
    }

    @Test
    fun `should emit an empty stream when an exception is thrown when calling the invoke method`() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(repository.getPopularMovies())
            .thenThrow(exception)

        //When
        val result = getPopularMoviesUseCase.invoke(
            GetPopularMoviesUseCase.Params(
                PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )

        //Then
        verify(repository).getPopularMovies()
        Truth.assertThat(result.toList()).isEmpty()
    }

}