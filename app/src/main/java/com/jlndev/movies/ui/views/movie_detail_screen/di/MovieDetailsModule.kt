package com.jlndev.movies.ui.views.movie_detail_screen.di

import com.jlndev.movies.core.data.remote.MovieService
import com.jlndev.movies.ui.views.movie_detail_screen.domain.repository.MovieDetailRepository
import com.jlndev.movies.ui.views.movie_detail_screen.domain.repository.MovieDetailRepositoryImpl
import com.jlndev.movies.ui.views.movie_detail_screen.domain.source.MovieDetailsRemoteDataSource
import com.jlndev.movies.ui.views.movie_detail_screen.domain.source.MovieDetailsRemoteDataSourceImpl
import com.jlndev.movies.ui.views.movie_detail_screen.domain.usecase.GetMovieDetailUseCase
import com.jlndev.movies.ui.views.movie_detail_screen.domain.usecase.GetMovieDetailUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailsModule {

    @Provides
    @Singleton
    fun provideMovieDetailsDataSource(service: MovieService) : MovieDetailsRemoteDataSource {
        return MovieDetailsRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(dataSource: MovieDetailsRemoteDataSource) : MovieDetailRepository {
        return MovieDetailRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideMovieSearchUseCase(repository: MovieDetailRepository) : GetMovieDetailUseCase {
        return GetMovieDetailUseCaseImpl(repository)
    }
}