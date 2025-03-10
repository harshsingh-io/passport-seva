// app/src/main/java/com/harshsinghio/passportseva/presentation/screens/services/ServicesScreen.kt
package com.harshsinghio.passportseva.presentation.screens.services

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.harshsinghio.passportseva.domain.model.Service
import com.harshsinghio.passportseva.presentation.common.components.PassportSevaAppBar
import com.harshsinghio.passportseva.presentation.common.components.PassportSevaBottomNavBar
import com.harshsinghio.passportseva.presentation.common.components.ServiceItem

/**
 * Screen that displays all available services in the Passport Seva application
 *
 * @param onNavigateBack Callback to navigate back to the previous screen
 * @param onNavigateToHome Callback to navigate to home screen
 * @param onNavigateToProfile Callback to navigate to profile screen
 * @param onServiceClick Callback when a service is clicked
 * @param viewModel ViewModel for services screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServicesScreen(
    onNavigateBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onServiceClick: (Service) -> Unit,
    viewModel: ServicesViewModel = viewModel()
) {
    val scrollState = rememberScrollState()
    val services by viewModel.services
    val uiState by viewModel.uiState

    Scaffold(
        topBar = {
            PassportSevaAppBar(
                userInitial = "J",
                showBackButton = false,
                onBackClick = onNavigateBack
            )
        },
        bottomBar = {
            PassportSevaBottomNavBar(
                currentRoute = "services",
                onHomeClick = onNavigateToHome,
                onServicesClick = { /* Already on services */ },
                onProfileClick = onNavigateToProfile
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Title
            Text(
                text = "All Services",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            // Services list
            services.forEach { service ->
                // Map icon type to Icons
                val icon = when (service.iconType) {
                    "calendar" -> Icons.Default.CalendarToday
                    "file_text" -> Icons.Default.Description
                    "credit_card" -> Icons.Default.CreditCard
                    "map_pin" -> Icons.Default.LocationOn
                    "message_square" -> Icons.Default.MedicalServices
                    "help_circle" -> Icons.Default.Help
                    else -> Icons.Default.Description
                }

                ServiceItem(
                    icon = icon,
                    title = service.title,
                    description = service.description,
                    onClick = {
                        viewModel.onServiceSelected(service)
                        onServiceClick(service)
                    }
                )
            }
        }
    }
}