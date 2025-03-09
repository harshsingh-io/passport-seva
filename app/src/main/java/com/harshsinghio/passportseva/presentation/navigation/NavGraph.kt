package com.harshsinghio.passportseva.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.harshsinghio.passportseva.presentation.screens.appointment.AppointmentScreen
import com.harshsinghio.passportseva.presentation.screens.appointmentdetails.AppointmentDetailsScreen
import com.harshsinghio.passportseva.presentation.screens.home.HomeScreen
import com.harshsinghio.passportseva.presentation.screens.login.LoginScreen
import com.harshsinghio.passportseva.presentation.screens.profile.ProfileScreen
import com.harshsinghio.passportseva.presentation.screens.register.RegisterScreen
import com.harshsinghio.passportseva.presentation.screens.services.ServicesScreen
import com.harshsinghio.passportseva.presentation.screens.status.StatusScreen

/**
 * Main navigation graph for the Passport Seva app
 *
 * @param navController The NavHostController to use for navigation
 * @param startDestination The starting destination route
 */
@Composable
fun PassportSevaNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Home screen
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToLogin = { navController.navigate(Screen.Login.route) },
                onNavigateToRegister = { navController.navigate(Screen.Register.route) },
                onNavigateToAppointment = { navController.navigate(Screen.Appointment.route) },
                onNavigateToStatus = { navController.navigate(Screen.Status.route) },
                onNavigateToDocumentAdvisor = { navController.navigate(Screen.DocumentAdvisor.route) },
                onNavigateToFeeCalculator = { navController.navigate(Screen.FeeCalculator.route) },
                onNavigateToLocateCentre = { navController.navigate(Screen.LocateCentre.route) },
                onNavigateToServices = { navController.navigate(Screen.Services.route) },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }

        // Login screen
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateBack = { navController.popBackStack() },
                onLoginSuccess = {
                    // Pop back to home and refresh
                    navController.popBackStack(Screen.Home.route, false)
                },
                onNavigateToRegister = {
                    // Navigate to register within the login flow
                    navController.navigate(Screen.Register.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // Appointment screen
        composable(Screen.Appointment.route) {
            AppointmentScreen(
                onNavigateBack = { navController.popBackStack() },
                onAppointmentBooked = {
                    // Pop back to home after booking
                    navController.popBackStack(Screen.Home.route, false)
                },
                navController = navController  // Add this parameter
            )
        }


        // Status screen
        composable(Screen.Status.route) {
            StatusScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // Add other screens as they are implemented

        // Register screen
        composable(Screen.Register.route) {
            RegisterScreen(
                onNavigateBack = { navController.popBackStack() },
                onRegisterSuccess = {
                    // Navigate back to home after successful registration
                    navController.popBackStack(Screen.Home.route, false)
                }
            )
        }
        // Profile Screen
        composable(route = Screen.Profile.route) {
            ProfileScreen(
                onNavigateToHome = { navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }},
                onNavigateToServices = { navController.navigate(Screen.Services.route) {
                    popUpTo(Screen.Home.route)
                }},
                onLogout = {
                    // Navigate to login screen and clear backstack
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }
        // Services Screen
        composable(route = Screen.Services.route) {
            ServicesScreen(
                onNavigateBack = { navController.navigateUp() },
                onNavigateToHome = { navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }},
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) {
                    popUpTo(Screen.Home.route)
                }},
                onServiceClick = { service ->
                    // Handle service navigation based on service.id
                    when (service.id) {
                        "appointment" -> navController.navigate(Screen.Appointment.route)
                        "document" -> { /* Navigate to document advisor */ }
                        "fee" -> { /* Navigate to fee calculator */ }
                        "locate" -> { /* Navigate to locate centre */ }
                        else -> { /* Handle other services */ }
                    }
                }
            )
        }


        // Status screen
        composable(Screen.Status.route) {
            StatusScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // Register screen
        composable(Screen.Register.route) {
            RegisterScreen(
                onNavigateBack = { navController.popBackStack() },
                onRegisterSuccess = {
                    // Navigate back to home after successful registration
                    navController.popBackStack(Screen.Home.route, false)
                }
            )
        }

        // Document Advisor screen (placeholder)
        composable(Screen.DocumentAdvisor.route) {
            // Create a simple placeholder
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Document Advisor Screen",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        // Fee Calculator screen (placeholder)
        composable(Screen.FeeCalculator.route) {
            // Create a simple placeholder
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Fee Calculator Screen",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        // Locate Centre screen (placeholder)
        composable(Screen.LocateCentre.route) {
            // Create a simple placeholder
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Locate Centre Screen",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        composable(route = Screen.AppointmentDetails.route) {
            AppointmentDetailsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}