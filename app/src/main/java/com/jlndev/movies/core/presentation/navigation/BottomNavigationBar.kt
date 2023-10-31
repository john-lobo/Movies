package com.jlndev.movies.core.presentation.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jlndev.movies.ui.theme.black
import com.jlndev.movies.ui.theme.yellow

@Composable
fun BottomNavigationBar(
    navController: NavController,
) {
    val items = listOf(
        BottomNavItem.MoviePopular,
        BottomNavItem.MovieSeach,
        BottomNavItem.MovieFavorite,
    )

    BottomNavigation(
        contentColor = yellow,
        backgroundColor = black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { destination ->
            this.BottomNavigationItem(
                selected = currentRoute == destination.route,
                label = { Text(text = destination.title) },
                icon = { Icon(imageVector = destination.icon, contentDescription = null) },
                onClick = {
                    navController.navigate(destination.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController())
}