package com.harshsinghio.passportseva.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.harshsinghio.passportseva.presentation.navigation.PassportSevaNavGraph

/**
 * Root composable for the Passport Seva application.
 * Sets up the navigation and overall app structure.
 */
@Composable
fun PassportSevaApp() {
    // Create and remember a NavController
    val navController = rememberNavController()

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
class PassportSevaNavigationActions(private val navController: androidx.navigation.NavController) {

    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }

    val popBackStack: () -> Unit = {
        navController.popBackStack()
    }

    val navigateToHome: () -> Unit = {
        navController.navigate(com.harshsinghio.passportseva.presentation.navigation.Screen.Home.route) {
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

    // Add more navigation actions as needed
}