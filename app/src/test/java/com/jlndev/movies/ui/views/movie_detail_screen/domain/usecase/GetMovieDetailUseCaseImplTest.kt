package com.jlndev.movies.ui.views.movie_detail_screen.domain.usecase

import androidx.paging.PagingConfig
import com.google.common.truth.Truth
import com.jlndev.movies.TestDispatcherRule
import com.jlndev.movies.core.domain.model.MovieDetailFactory
import com.jlndev.movies.core.domain.model.MovieFactory
import com.jlndev.movies.core.domain.model.PagingSourceMoviesFactory
import com.jlndev.movies.core.util.ResultData
import com.jlndev.movies.ui.views.movie_detail_screen.domain.repository.MovieDetailRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMovieDetailUseCaseImplTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieDetailRepository: MovieDetailRepository

    private val movieFactory = MovieFactory().create(MovieFactory.Poster.Avengers)

    private val movieDetailsFactory = MovieDetailFactory().create(MovieDetailFactory.Poster.Avengers)

    private val pagingSourceFake = PagingSourceMoviesFactory().create(listOf(movieFactory))

    private val getMovieDetailUseCase by lazy {
        GetMovieDetailUseCaseImpl(movieDetailRepository)
    }

    @Test
    fun `should return Success from ResultStatus when get both requests return success`() = runTest {
        //Given
        whenever(movieDetailRepository.getMovieDetails(movieFactory.id))
            .thenReturn(movieDetailsFactory)

        whenever(movieDetailRepository.getMoviesSimilar(movieFactory.id))
            .thenReturn(pagingSourceFake)

        //When
        val result = getMovieDetailUseCase.invoke(
            GetMovieDetailUseCase.Params(
                movieId = movieFactory.id,
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )

        //Then
        verify(movieDetailRepository).getMovieDetails(movieFactory.id)
        verify(movieDetailRepository).getMoviesSimilar(movieFactory.id)
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result is ResultData.Success).isTrue()
    }

    @Test
    fun `should return Error from ResultStatus when get movieDetails request returns error`() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(movieDetailRepository.getMovieDetails(movieFactory.id))
            .thenThrow(exception)

        //When
        val result = getMovieDetailUseCase.invoke(
            GetMovieDetailUseCase.Params(
                movieId = movieFactory.id,
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )

        //Then
        verify(movieDetailRepository).getMovieDetails(movieFactory.id)
        Truth.assertThat(result is ResultData.Error).isTrue()
    }

    @Test
    fun `should return Error from ResultStatus when get similar movies request returns error`() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(movieDetailRepository.getMoviesSimilar(movieFactory.id))
            .thenThrow(exception)

        //When
        val result = getMovieDetailUseCase.invoke(
            GetMovieDetailUseCase.Params(
                movieId = movieFactory.id,
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )

        //Then
        verify(movieDetailRepository).getMoviesSimilar(movieFactory.id)
        Truth.assertThat(result is ResultData.Error).isTrue()
    }
}