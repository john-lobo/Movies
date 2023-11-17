package com.jlndev.movies.ui.views.movie_favorite_screen.di

import com.jlndev.movies.core.data.local.dao.MovieDao
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.repository.MovieFavoriteRepository
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.repository.MovieFavoriteRepositoryImpl
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.source.MovieFavoriteLocalDataSource
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.source.MovieFavoriteLocalDataSourceImpl
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase.AddMovieFavoriteUseCase
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase.AddMovieFavoriteUseCaseImpl
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase.DeleteMovieFavoriteUseCase
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase.DeleteMovieFavoriteUseCaseImpl
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase.GetMoviesFavoriteUseCase
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase.GetMoviesFavoriteUseCaseImpl
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase.IsMovieFavoriteUseCase
import com.jlndev.movies.ui.views.movie_favorite_screen.domain.usecase.IsMovieFavoriteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieFavoriteModule {

    @Provides
    @Singleton
    fun provideMovieFavoriteLocalDataSource(dao: MovieDao): MovieFavoriteLocalDataSource {
        return MovieFavoriteLocalDataSourceImpl(dao)
    }

    @Provides
    @Singleton
    fun provideMovieFavoriteRepository(dataSource: MovieFavoriteLocalDataSource): MovieFavoriteRepository {
        return MovieFavoriteRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideAddMovieFavoriteUseCase(repository: MovieFavoriteRepository): AddMovieFavoriteUseCase {
        return AddMovieFavoriteUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteMovieFavoriteUseCase(repository: MovieFavoriteRepository): DeleteMovieFavoriteUseCase {
        return DeleteMovieFavoriteUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideGetMoviesFavoriteUseCase(repository: MovieFavoriteRepository): GetMoviesFavoriteUseCase {
        return GetMoviesFavoriteUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideIsMovieFavoriteUseCase(repository: MovieFavoriteRepository): IsMovieFavoriteUseCase {
        return IsMovieFavoriteUseCaseImpl(repository)
    }
}