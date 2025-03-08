package com.harshsinghio.passportseva.presentation.common.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Common bottom navigation bar for the Passport Seva application
 *
 * @param currentRoute Current active route/screen
 * @param onHomeClick Callback when Home tab is clicked
 * @param onServicesClick Callback when Services tab is clicked
 * @param onProfileClick Callback when Profile tab is clicked
 * @param modifier Modifier to be applied to the navigation bar
 */
@Composable
fun PassportSevaBottomNavBar(
    currentRoute: String,
    onHomeClick: () -> Unit,
    onServicesClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = onHomeClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )
            },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = currentRoute == "services",
            onClick = onServicesClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Description,
                    contentDescription = "Services"
                )
            },
            label = { Text("Services") }
        )
        NavigationBarItem(
            selected = currentRoute == "profile",
            onClick = onProfileClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Profile"
                )
            },
            label = { Text("Profile") }
        )
    }
}
