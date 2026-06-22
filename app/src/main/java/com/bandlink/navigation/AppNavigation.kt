package com.bandlink.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bandlink.screens.*
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val startDestination =
        if (FirebaseAuth.getInstance().currentUser != null)
            "home"
        else
            "login"

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController)
        }

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
            ViewBandsScreen(navController)
        }
        composable("updateBand/{bandId}") { backStackEntry ->

            val bandId =
                backStackEntry.arguments?.getString("bandId") ?: ""

            UpdateBandScreen(
                navController = navController,
                bandId  = bandId
            )
        }

        composable("createEvent") {
            CreateEventScreen()
        }

        composable("viewEvents") {
            ViewEventsScreen(navController)
        }
        composable("updateEvent/{eventId}") { backStackEntry ->


            val eventId =
                backStackEntry.arguments?.getString("eventId") ?: ""

            UpdateEventScreen(
                navController = navController,
                eventId = eventId
            )


        }



    }
}