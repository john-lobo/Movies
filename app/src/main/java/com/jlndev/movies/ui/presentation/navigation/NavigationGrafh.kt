package com.jlndev.movies.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jlndev.movies.movie_popular_feature.presentation.MoviePopularScreen
import com.jlndev.movies.movie_popular_feature.presentation.MoviePopularViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MoviePopular.route
    ) {
        composable(BottomNavItem.MoviePopular.route) {

            val viewModel: MoviePopularViewModel = hiltViewModel()

            MoviePopularScreen(
                uiState = viewModel.uiState,
                navigationToDetailMovie = {})
        }

        composable(BottomNavItem.MovieSeach.route) {

        }

        composable(BottomNavItem.MovieFavorite.route) {

        }
    }
}