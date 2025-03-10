package com.harshsinghio.passportseva.presentation.navigation

/**
 * Sealed class representing the different screens in the app.
 * Each screen has a unique route for navigation.
 */
sealed class Screen(val route: String) {
    // Main screens
    object Home : Screen("home")
    object Services : Screen("services")
    object Profile : Screen("profile")

    // Feature screens
    object Login : Screen("login")
    object Register : Screen("register")
    object Appointment : Screen("appointment")
    object Status : Screen("status")
    object DocumentAdvisor : Screen("document_advisor")
    object FeeCalculator : Screen("fee_calculator")
    object LocateCentre : Screen("locate_centre")
    object AppointmentDetails : Screen("appointment_details")

    // New screens for services
    object Annexures : Screen("annexures")
    object Grievance : Screen("grievance")
    object FAQ : Screen("faq")


    /**
     * Utility to create route with arguments
     *
     * @param args Optional arguments to append to the route
     * @return Route string with arguments
     */
    fun createRoute(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    /**
     * Get a Screen from a route string
     *
     * @param route The route string
     * @return The corresponding Screen object
     */
    companion object {
        fun fromRoute(route: String): Screen {
            // Extract the base route without arguments
            val baseRoute = route.split("/").first()

            return when (baseRoute) {
                Home.route -> Home
                Services.route -> Services
                Profile.route -> Profile
                Login.route -> Login
                Register.route -> Register
                Appointment.route -> Appointment
                Status.route -> Status
                DocumentAdvisor.route -> DocumentAdvisor
                FeeCalculator.route -> FeeCalculator
                LocateCentre.route -> LocateCentre
                Annexures.route -> Annexures
                Grievance.route -> Grievance
                FAQ.route -> FAQ
                AppointmentDetails.route -> AppointmentDetails
                else -> Home // Default to Home screen
            }
        }
    }
}