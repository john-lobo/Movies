package com.jlndev.movies.core.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.ui.views.movie_popular_screen.domain.source.MoviePopularRemoteDataSource

class MoviePagingSource(
    private val remoteDataSource: MoviePopularRemoteDataSource
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(20) ?: anchorPage?.nextKey?.minus(20)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {

            val pageNumber = params.key ?: 1
            val moviePaging = remoteDataSource.getPopularMovies(pageNumber)
            val movies = moviePaging.movies
            val totalPages = moviePaging.totalPages

            LoadResult.Page(
                data = movies,
                prevKey = if(pageNumber == 1) null else pageNumber - 1,
                nextKey = if(pageNumber == totalPages) null else pageNumber + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}