package com.harshsinghio.passportseva.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.harshsinghio.passportseva.domain.model.QuickAction
import com.harshsinghio.passportseva.domain.model.Service
import com.harshsinghio.passportseva.presentation.common.components.PassportSevaAppBar
import com.harshsinghio.passportseva.presentation.common.components.PassportSevaBottomNavBar
import com.harshsinghio.passportseva.presentation.common.components.SearchBar
import com.harshsinghio.passportseva.presentation.screens.home.components.QuickActionsSection
import com.harshsinghio.passportseva.presentation.screens.home.components.ServicesSection
import com.harshsinghio.passportseva.presentation.screens.home.components.UpdatesSection

/**
 * Main home screen of the Passport Seva application
 *
 * @param onNavigateToLogin Callback to navigate to login screen
 * @param onNavigateToRegister Callback to navigate to register screen
 * @param onNavigateToAppointment Callback to navigate to appointment screen
 * @param onNavigateToStatus Callback to navigate to status screen
 * @param onNavigateToDocumentAdvisor Callback to navigate to document advisor screen
 * @param onNavigateToFeeCalculator Callback to navigate to fee calculator screen
 * @param onNavigateToLocateCentre Callback to navigate to locate centre screen
 * @param onNavigateToServices Callback to navigate to services screen
 * @param onNavigateToProfile Callback to navigate to profile screen
 * @param viewModel ViewModel for the home screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToAppointment: () -> Unit,
    onNavigateToStatus: () -> Unit,
    onNavigateToDocumentAdvisor: () -> Unit,
    onNavigateToFeeCalculator: () -> Unit,
    onNavigateToLocateCentre: () -> Unit,
    onNavigateToServices: () -> Unit,
    onNavigateToProfile: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val activeTab by viewModel.activeTab
    val quickActions by viewModel.quickActions
    val services by viewModel.services
    val updates by viewModel.updates
    val snackbarHostState = remember { SnackbarHostState() }
    val scrollState = rememberScrollState()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            PassportSevaAppBar(
                showBackButton = false,
                onNotificationClick = {
                    // Handle notification click in a real app
                },
                onProfileClick = onNavigateToProfile
            )
        },
        bottomBar = {
            PassportSevaBottomNavBar(
                currentRoute = "home",
                onHomeClick = { viewModel.setActiveTab("home") },
                onServicesClick = {
                    viewModel.setActiveTab("services")
                    onNavigateToServices()
                },
                onProfileClick = {
                    viewModel.setActiveTab("profile")
                    onNavigateToProfile()
                }
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
            Spacer(modifier = Modifier.height(8.dp))

            // Search
            SearchBar(
                placeholder = "Search services...",
                onSearch = {
                    // Handle search in a real app
                }
            )

            // Quick Actions
            QuickActionsSection(
                quickActions = quickActions,
                onQuickActionClick = { action ->
                    handleQuickActionClick(
                        action = action,
                        onLogin = onNavigateToLogin,
                        onRegister = onNavigateToRegister,
                        onBook = onNavigateToAppointment,
                        onTrack = onNavigateToStatus,
                        viewModel = viewModel
                    )
                }
            )

            // Services
            ServicesSection(
                services = services,
                onServiceClick = { service ->
                    handleServiceClick(
                        service = service,
                        onAppointment = onNavigateToAppointment,
                        onDocumentAdvisor = onNavigateToDocumentAdvisor,
                        onFeeCalculator = onNavigateToFeeCalculator,
                        onLocateCentre = onNavigateToLocateCentre,
                        viewModel = viewModel
                    )
                },
                onViewAllClick = onNavigateToServices
            )

            // Updates
            UpdatesSection(
                updates = updates
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

/**
 * Handle clicks on quick action items
 */
private fun handleQuickActionClick(
    action: QuickAction,
    onLogin: () -> Unit,
    onRegister: () -> Unit,
    onBook: () -> Unit,
    onTrack: () -> Unit,
    viewModel: HomeViewModel
) {
    when (action.id) {
        "login" -> onLogin()
        "register" -> onRegister()
        "book" -> onBook()
        "track" -> onTrack()
        else -> viewModel.handleMenuItemClick(action.label)
    }
}

/**
 * Handle clicks on service items
 */
private fun handleServiceClick(
    service: Service,
    onAppointment: () -> Unit,
    onDocumentAdvisor: () -> Unit,
    onFeeCalculator: () -> Unit,
    onLocateCentre: () -> Unit,
    viewModel: HomeViewModel
) {
    when (service.id) {
        "appointment" -> onAppointment()
        "document" -> onDocumentAdvisor()
        "fee" -> onFeeCalculator()
        "locate" -> onLocateCentre()
        else -> viewModel.handleMenuItemClick(service.title)
    }
}