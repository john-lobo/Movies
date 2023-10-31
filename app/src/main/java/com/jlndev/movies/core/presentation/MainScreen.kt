package com.jlndev.movies.core.presentation


import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.jlndev.movies.core.presentation.navigation.BottomNavigationBar
import com.jlndev.movies.core.presentation.navigation.NavigationGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        content = {
            NavigationGraph(navController = navController)
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    )
}