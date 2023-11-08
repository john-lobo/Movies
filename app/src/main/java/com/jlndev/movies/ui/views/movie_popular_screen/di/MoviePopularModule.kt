package com.jlndev.movies.ui.views.movie_popular_screen.di

import com.jlndev.movies.core.data.remote.MovieService
import com.jlndev.movies.ui.views.movie_popular_screen.domain.repository.MoviePopularRepository
import com.jlndev.movies.ui.views.movie_popular_screen.domain.repository.MoviePopularRepositoryImpl
import com.jlndev.movies.ui.views.movie_popular_screen.domain.source.MoviePopularRemoteDataSource
import com.jlndev.movies.ui.views.movie_popular_screen.domain.source.MoviePopularRemoteDataSourceImpl
import com.jlndev.movies.ui.views.movie_popular_screen.domain.usecase.GetPopularMoviesUseCase
import com.jlndev.movies.ui.views.movie_popular_screen.domain.usecase.GetPopularMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviePopularModule {
    @Provides
    @Singleton
    fun provideMovieDataSource(service: MovieService) : MoviePopularRemoteDataSource {
        return MoviePopularRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(dataSource: MoviePopularRemoteDataSource) : MoviePopularRepository {
        return MoviePopularRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideMovieUseCase(repository: MoviePopularRepository) : GetPopularMoviesUseCase {
        return GetPopularMoviesUseCaseImpl(repository)
    }
}