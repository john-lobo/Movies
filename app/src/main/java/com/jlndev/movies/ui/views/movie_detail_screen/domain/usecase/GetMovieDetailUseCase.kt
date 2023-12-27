package com.jlndev.movies.ui.views.movie_detail_screen.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.core.domain.model.MovieDetails
import com.jlndev.movies.core.util.ResultData
import kotlinx.coroutines.flow.Flow

interface  GetMovieDetailUseCase {
    suspend operator fun invoke(params: Params) : ResultData<Pair<Flow<PagingData<Movie>>, MovieDetails>>
    data class Params(val movieId: Int, val pagingConfig: PagingConfig)
}