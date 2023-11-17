package com.jlndev.movies

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jlndev.movies.ui.presentation.navigation.BottomNavigationBar
import com.jlndev.movies.ui.presentation.navigation.DetailScreenNav
import com.jlndev.movies.ui.presentation.navigation.NavigationGraph
import com.jlndev.movies.ui.presentation.navigation.currentRoute
import com.jlndev.movies.ui.theme.MoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MoviesTheme {
                MainScreen(navController = rememberNavController())
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        content = {
            NavigationGraph(Modifier.padding(it), navController = navController)
        },
        bottomBar = {
            if(currentRoute(navHostController = navController) != DetailScreenNav.DetailScreen.route) {
                BottomNavigationBar(navController = navController)
            }
        }
    )
}