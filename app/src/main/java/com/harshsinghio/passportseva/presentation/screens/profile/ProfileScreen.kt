package com.harshsinghio.passportseva.presentation.screens.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.harshsinghio.passportseva.presentation.common.components.PassportSevaAppBar
import com.harshsinghio.passportseva.presentation.common.components.PassportSevaBottomNavBar

/**
 * Profile screen that displays user account settings and options
 *
 * @param onNavigateToHome Callback to navigate to home screen
 * @param onNavigateToServices Callback to navigate to services screen
 * @param onLogout Callback when user logs out
 * @param viewModel ViewModel for the profile screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToServices: () -> Unit,
    onLogout: () -> Unit,
    viewModel: ProfileViewModel = viewModel()
) {
    val scrollState = rememberScrollState()
    val uiState by viewModel.uiState

    Scaffold(
        topBar = {
            PassportSevaAppBar(
                userInitial = "U",
                showBackButton = false,
                showNotification = false
            )
        },
        bottomBar = {
            PassportSevaBottomNavBar(
                currentRoute = "profile",
                onHomeClick = onNavigateToHome,
                onServicesClick = onNavigateToServices,
                onProfileClick = { /* Already on profile */ }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Profile header
            ListItem(
                headlineContent = { Text(uiState.userName) },
                supportingContent = { Text(uiState.userEmail) },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Account",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            // Account settings
            Text(
                text = "Account Settings",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            ListItem(
                headlineContent = { Text("Personal Information") },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Personal Information"
                    )
                },
                trailingContent = { /* Add optional trailing content here */ },
                modifier = Modifier.clickable { viewModel.onSettingsItemClick("Personal Information") }
            )

            ListItem(
                headlineContent = { Text("Notification Preferences") },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications"
                    )
                },
                modifier = Modifier.clickable { viewModel.onSettingsItemClick("Notification Preferences") }
            )

            ListItem(
                headlineContent = { Text("Security Settings") },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = "Security"
                    )
                },
                modifier = Modifier.clickable { viewModel.onSettingsItemClick("Security Settings") }
            )

            Divider()

            // Support
            Text(
                text = "Support",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            ListItem(
                headlineContent = { Text("Help Center") },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.Help,
                        contentDescription = "Help"
                    )
                },
                modifier = Modifier.clickable { viewModel.onSettingsItemClick("Help Center") }
            )

            ListItem(
                headlineContent = { Text("About") },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "About"
                    )
                },
                modifier = Modifier.clickable { viewModel.onSettingsItemClick("About") }
            )

            Divider()

            // Logout
            ListItem(
                headlineContent = { Text("Logout") },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Logout"
                    )
                },
                modifier = Modifier.clickable(onClick = onLogout)
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}