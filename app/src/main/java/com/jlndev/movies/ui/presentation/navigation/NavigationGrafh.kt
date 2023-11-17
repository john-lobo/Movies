package com.jlndev.movies.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jlndev.movies.core.util.Constants
import com.jlndev.movies.ui.views.movie_detail_screen.presentation.MovieDetailScreen
import com.jlndev.movies.ui.views.movie_detail_screen.presentation.MovieDetailViewModel
import com.jlndev.movies.ui.views.movie_favorite_screen.presentation.MovieFavoriteScreen
import com.jlndev.movies.ui.views.movie_favorite_screen.presentation.state.MovieFavoriteViewModel
import com.jlndev.movies.ui.views.movie_popular_screen.presentation.MoviePopularScreen
import com.jlndev.movies.ui.views.movie_popular_screen.presentation.MoviePopularViewModel
import com.jlndev.movies.ui.views.movie_search_screen.presentation.MovieSearchScreen
import com.jlndev.movies.ui.views.movie_search_screen.presentation.MovieSearchViewModel

@Composable
fun NavigationGraph(modifier: Modifier, navController: NavHostController) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = BottomNavItem.MoviePopular.route
    ) {
        composable(BottomNavItem.MoviePopular.route) {

            val viewModel: MoviePopularViewModel = hiltViewModel()

            MoviePopularScreen(
                uiState = viewModel.uiState,
                navigationToDetailMovie = {
                    navController.navigate(DetailScreenNav.DetailScreen.passMovieId(it))
                })
        }

        composable(BottomNavItem.MovieSeach.route) {

            val viewModel: MovieSearchViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val onEvent: (String) -> Unit = { viewModel.event(it) }
            val onFetch: (String) -> Unit = { viewModel.fetch(it) }
            MovieSearchScreen(
                uiState = uiState,
                onEvent = onEvent,
                onFetch = onFetch,
                navigateToDetailMovie = {
                    navController.navigate(DetailScreenNav.DetailScreen.passMovieId(it))
                }
            )
        }

        composable(BottomNavItem.MovieFavorite.route) {
            val viewModel : MovieFavoriteViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            MovieFavoriteScreen(
                uiState = uiState,
                navigateToDetailScreen = {
                    navController.navigate(DetailScreenNav.DetailScreen.passMovieId(it))
                }
            )
        }

        composable(
            route = DetailScreenNav.DetailScreen.route,
            arguments = listOf(
                navArgument(Constants.MOVIE_DETAIL_ARGUMENT_KEY) {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            val viewModel: MovieDetailViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val onAddFavorite = viewModel::onAddFavorite

            MovieDetailScreen(
                uiState = uiState,
                onAddFavorite = onAddFavorite,
            )
        }
    }
}