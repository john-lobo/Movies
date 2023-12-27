package com.jlndev.movies.ui.views.movie_search_screen.di

import com.jlndev.movies.core.data.remote.MovieService
import com.jlndev.movies.ui.views.movie_search_screen.domain.repository.MovieSearchRepository
import com.jlndev.movies.ui.views.movie_search_screen.domain.repository.MovieSearchRepositoryImpl
import com.jlndev.movies.ui.views.movie_search_screen.domain.source.MovieSearchRemoteDataSource
import com.jlndev.movies.ui.views.movie_search_screen.domain.source.MovieSearchRemoteDataSourceImpl
import com.jlndev.movies.ui.views.movie_search_screen.domain.usecase.GetMovieSearchUseCase
import com.jlndev.movies.ui.views.movie_search_screen.domain.usecase.GetMovieSearchUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieSearchModule {

    @Provides
    @Singleton
    fun provideMovieSearchDataSource(service: MovieService) : MovieSearchRemoteDataSource {
        return MovieSearchRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideMovieSearchRepository(dataSource: MovieSearchRemoteDataSource) : MovieSearchRepository {
        return MovieSearchRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideMovieSearchUseCase(repository: MovieSearchRepository) : GetMovieSearchUseCase {
        return GetMovieSearchUseCaseImpl(repository)
    }
}