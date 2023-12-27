package com.jlndev.movies.core.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jlndev.movies.core.domain.model.MovieSearch
import com.jlndev.movies.ui.views.movie_search_screen.domain.source.MovieSearchRemoteDataSource

class MovieSearchPagingSource(
    private val query: String,
    private val remoteDataSource: MovieSearchRemoteDataSource
): PagingSource<Int, MovieSearch>() {
    override fun getRefreshKey(state: PagingState<Int, MovieSearch>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(20) ?: anchorPage?.nextKey?.minus(20)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSearch> {
        return try {
            val pageNumber = params.key ?: 1
            val response = remoteDataSource.getSearchMovies(query, pageNumber)
            val movies = response.movies
            val totalPages = response.totalPages

            LoadResult.Page(
                data = movies,
                prevKey = if(pageNumber == 1) null else pageNumber - 1,
                nextKey = if(pageNumber == totalPages) null else pageNumber + 1
            )

        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

}