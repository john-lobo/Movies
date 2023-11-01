package com.jlndev.movies.movie_popular_feature.di

import com.jlndev.movies.core.data.remote.MovieService
import com.jlndev.movies.movie_popular_feature.domain.repository.MoviePopularRepository
import com.jlndev.movies.movie_popular_feature.domain.repository.MoviePopularRepositoryImpl
import com.jlndev.movies.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import com.jlndev.movies.movie_popular_feature.domain.source.MoviePopularRemoteDataSourceImpl
import com.jlndev.movies.movie_popular_feature.domain.usecase.GetPopularMoviesUseCase
import com.jlndev.movies.movie_popular_feature.domain.usecase.GetPopularMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviePopularFeatureModule {
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