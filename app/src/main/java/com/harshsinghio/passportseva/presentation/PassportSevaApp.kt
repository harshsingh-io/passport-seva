package com.harshsinghio.passportseva.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.harshsinghio.passportseva.presentation.navigation.PassportSevaNavGraph
import com.harshsinghio.passportseva.presentation.navigation.Screen

/**
 * Root composable for the Passport Seva application.
 * Sets up the navigation and overall app structure.
 */
@Composable
fun PassportSevaApp() {
    // Create and remember a NavController
    val navController = rememberNavController()

    // Track current screen for bottom navigation
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    // Set up navigation state listener
    navController.addOnDestinationChangedListener { _, destination, _ ->
        currentScreen = Screen.fromRoute(destination.route ?: "")
    }

    // Remember the navigation actions
    val navigationActions = remember(navController) {
        PassportSevaNavigationActions(navController)
    }

    // Set up the navigation graph
    PassportSevaNavGraph(
        navController = navController
    )
}

/**
 * Helper class to handle navigation actions throughout the app
 */
class PassportSevaNavigationActions(private val navController: NavController) {

    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }

    val popBackStack: () -> Unit = {
        navController.popBackStack()
    }

    val navigateToHome: () -> Unit = {
        navController.navigate(Screen.Home.route) {
            // Pop up to the start destination of the graph to avoid building up a stack
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }

    val navigateToServices: () -> Unit = {
        navController.navigate(Screen.Services.route) {
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToProfile: () -> Unit = {
        navController.navigate(Screen.Profile.route) {
            launchSingleTop = true
            restoreState = true
        }
    }

    // Add more navigation actions as needed
}