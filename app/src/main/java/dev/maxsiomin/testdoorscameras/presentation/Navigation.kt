package dev.maxsiomin.testdoorscameras.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.maxsiomin.testdoorscameras.presentation.tabs_screen.TabScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.TabsScreen.route
    ) {
        composable(
            route = Screen.TabsScreen.route,
        ) {
            TabScreen()
        }
    }
}

