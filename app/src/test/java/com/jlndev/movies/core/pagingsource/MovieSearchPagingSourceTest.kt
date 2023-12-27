package com.jlndev.movies.core.pagingsource

import androidx.paging.PagingSource
import com.google.common.truth.Truth
import com.jlndev.movies.TestDispatcherRule
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.core.domain.model.MovieSearchPagingFactory
import com.jlndev.movies.ui.views.movie_search_screen.domain.source.MovieSearchRemoteDataSource
import com.nhaarman.mockitokotlin2.any
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
class MovieSearchPagingSourceTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var remoteDataSource: MovieSearchRemoteDataSource

    private val movieSearchPagingSource by lazy {
        MovieSearchPagingSource("avergens", remoteDataSource)
    }

    private val moviePagingFactory = MovieSearchPagingFactory().create()

    @Test
    fun `must return a success lad when load is called` () = runTest {
        //Given

        whenever(remoteDataSource.getSearchMovies(any(), any()))
            .thenReturn(moviePagingFactory)

        //When
        val result = movieSearchPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        val resultExpected = moviePagingFactory.movies

        //Then
        Truth.assertThat(
            PagingSource.LoadResult.Page(
                data = resultExpected,
                prevKey = null,
                nextKey = null
            )
        ).isEqualTo(result)
    }


    @Test
    fun `must return a error load result when load is called`() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(remoteDataSource.getSearchMovies(any(), any()))
            .thenThrow(exception)

        // When
        val result = movieSearchPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        //Then
        Truth.assertThat(PagingSource.LoadResult.Error<Int, Movie>(exception)).isEqualTo(result)
    }
}