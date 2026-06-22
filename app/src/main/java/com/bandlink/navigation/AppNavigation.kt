package com.bandlink.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bandlink.screens.*

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(navController)
        }

        composable("register") {
            RegisterScreen(navController)
        }

        composable("home") {
            HomeScreen(navController)
        }

        composable("createBand") {
            CreateBandScreen()
        }

        composable("viewBands") {
            ViewBandsScreen()
        }

        composable("createEvent") {
            CreateEventScreen()
        }

        composable("viewEvents") {
            ViewEventsScreen()
        }
    }
}