package com.jlndev.movies.ui.views.movie_search_screen.domain.usecase

import androidx.paging.PagingConfig
import com.google.common.truth.Truth
import com.jlndev.movies.TestDispatcherRule
import com.jlndev.movies.core.domain.model.MovieSearchFactory
import com.jlndev.movies.core.domain.model.PagingSourceMoviesSearchFactory
import com.jlndev.movies.ui.views.movie_search_screen.domain.repository.MovieSearchRepository
import com.nhaarman.mockitokotlin2.any
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
class GetMovieSearchUseCaseImplTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var repository: MovieSearchRepository

    private val getMovieSearchUseCase by lazy {
        GetMovieSearchUseCaseImpl(repository)
    }

    private val movie = MovieSearchFactory().create(MovieSearchFactory.Poster.Avengers)

    private val pagingSourceFake = PagingSourceMoviesSearchFactory()
        .create(listOf(movie))

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() = runTest {
        //Given
        whenever(repository.getPopularMovies(any()))
            .thenReturn(pagingSourceFake)

        //When
        val result = getMovieSearchUseCase.invoke(
            GetMovieSearchUseCase.Params(
                "",
                PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        ).first()

        //Then
        verify(repository).getPopularMovies(any())
        Truth.assertThat(result).isNotNull()
    }

    @Test
    fun `should emit an empty stream when an exception is thrown when calling the invoke method`() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(repository.getPopularMovies(any()))
            .thenThrow(exception)

        //When
        val result = getMovieSearchUseCase.invoke(
            GetMovieSearchUseCase.Params(
                "",
                PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )

        //Then
        verify(repository).getPopularMovies(any())
        Truth.assertThat(result.toList()).isEmpty()
    }
}