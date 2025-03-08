package com.harshsinghio.passportseva.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.harshsinghio.passportseva.presentation.screens.appointment.AppointmentScreen
import com.harshsinghio.passportseva.presentation.screens.home.HomeScreen
import com.harshsinghio.passportseva.presentation.screens.login.LoginScreen
import com.harshsinghio.passportseva.presentation.screens.register.RegisterScreen
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
                }
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
    }
}