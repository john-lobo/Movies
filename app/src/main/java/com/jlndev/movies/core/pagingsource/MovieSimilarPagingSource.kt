package com.jlndev.movies.core.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.core.util.ext.toMovies
import com.jlndev.movies.ui.views.movie_detail_screen.domain.source.MovieDetailsRemoteDataSource

class MovieSimilarPagingSource(
    private val remoteDataSource: MovieDetailsRemoteDataSource,
    private val movieId: Int
): PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(20) ?: anchorPage?.nextKey?.minus(20)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageNumber = params.key ?: 1
            val response = remoteDataSource.getMoviesSimilar(
                page = pageNumber,
                movieId = movieId)
            val movies = response.results

            LoadResult.Page(
                movies.toMovies(),
                prevKey = if(pageNumber == 1) null else pageNumber - 1,
                nextKey = if(movies.isEmpty()) null else pageNumber + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return LoadResult.Error(e)
        }
    }
}