package com.harshsinghio.passportseva.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.harshsinghio.passportseva.presentation.common.components.PassportSevaAppBar
import com.harshsinghio.passportseva.presentation.common.components.PassportSevaBottomNavBar
import com.harshsinghio.passportseva.presentation.common.theme.Blue100
import com.harshsinghio.passportseva.presentation.common.theme.Blue600

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
    val activeSection by viewModel.activeSection

    Scaffold(
        topBar = {
            PassportSevaAppBar(
                userInitial = uiState.userName.firstOrNull()?.toString() ?: "U",
                showBackButton = false,
                showNotification = false,
                onProfileClick = { /* Already on profile */ }
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
        if (activeSection != null) {
            when (activeSection) {
                "personal" -> {
                    PersonalInfoSection(
                        userData = viewModel.userData,
                        onBack = { viewModel.setActiveSection(null) }
                    )
                }
                else -> {
                    // Other sections like notifications, security, help, about
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            TextButton(
                                onClick = { viewModel.setActiveSection(null) }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ChevronLeft,
                                    contentDescription = "Back",
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Back")
                            }
                            Text(
                                text = when (activeSection) {
                                    "notifications" -> "Notification Preferences"
                                    "security" -> "Security Settings"
                                    "help" -> "Help Center"
                                    "about" -> "About"
                                    else -> ""
                                },
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.SemiBold
                            )
                            // Empty space for alignment
                            Spacer(modifier = Modifier.width(64.dp))
                        }
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(scrollState)
            ) {
                // We no longer need the header since we're using PassportSevaAppBar

                // Profile Card
                ProfileCard(
                    userName = uiState.userName,
                    userEmail = uiState.userEmail,
                    userMobile = uiState.userMobile,
                    isVerified = uiState.isVerified,
                    profileImageUrl = uiState.profileImageUrl,
                    onEditProfile = { /* Handle edit profile */ }
                )

                // Settings Sections
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    // Account Settings
                    AccountSettingsSection(
                        onPersonalInfoClick = { viewModel.setActiveSection("personal") },
                        onNotificationsClick = { viewModel.setActiveSection("notifications") },
                        onSecurityClick = { viewModel.setActiveSection("security") }
                    )

                    // Support
                    SupportSection(
                        onHelpCenterClick = { viewModel.setActiveSection("help") },
                        onAboutClick = { viewModel.setActiveSection("about") }
                    )

                    // Logout Button
                    Button(
                        onClick = onLogout,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red.copy(alpha = 0.1f),
                            contentColor = Color.Red
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Logout",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Logout")
                    }
                }
            }
        }
    }
}

// We removed the custom Header component since we're using PassportSevaAppBar

@Composable
fun ProfileCard(
    userName: String,
    userEmail: String,
    userMobile: String,
    isVerified: Boolean,
    profileImageUrl: String,
    onEditProfile: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(96.dp)
                .background(Blue600)
        ) {
            // Edit profile button
            Button(
                onClick = onEditProfile,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White.copy(alpha = 0.1f),
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Edit Profile")
            }

            // Profile picture
            Box(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .offset(y = 48.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape)
                        .background(Blue100)
                        .border(width = 4.dp, color = Color.White, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    if (profileImageUrl.isEmpty()) {
                        Text(
                            text = userName.firstOrNull()?.toString() ?: "U",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Blue600
                        )
                    }
                }

                // Camera button for changing profile picture
                IconButton(
                    onClick = { /* Handle changing profile picture */ },
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.BottomEnd)
                        .offset(x = (-4).dp, y = (-4).dp)
                        .background(Color.White, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Camera,
                        contentDescription = "Change profile picture",
                        tint = Blue600,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 56.dp, bottom = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = userName,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                if (isVerified) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Badge(
                        containerColor = Color(0xFFE6F4EA),
                        contentColor = Color(0xFF137333)
                    ) {
                        Text("Verified")
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = userEmail,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = userMobile,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun AccountSettingsSection(
    onPersonalInfoClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    onSecurityClick: () -> Unit
) {
    Column {
        Text(
            text = "Account Settings",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                // Personal Information item
                SettingsItem(
                    icon = Icons.Default.Person,
                    title = "Personal Information",
                    onClick = onPersonalInfoClick,
                    iconBackgroundColor = Blue100,
                    iconTint = Blue600
                )

                Divider()

                // Notifications item
                SettingsItem(
                    icon = Icons.Default.Notifications,
                    title = "Notification Preferences",
                    onClick = onNotificationsClick,
                    iconBackgroundColor = Blue100,
                    iconTint = Blue600
                )

                Divider()

                // Security item
                SettingsItem(
                    icon = Icons.Default.Shield,
                    title = "Security Settings",
                    onClick = onSecurityClick,
                    iconBackgroundColor = Blue100,
                    iconTint = Blue600
                )
            }
        }
    }
}

@Composable
fun SupportSection(
    onHelpCenterClick: () -> Unit,
    onAboutClick: () -> Unit
) {
    Column {
        Text(
            text = "Support",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                // Help Center item
                SettingsItem(
                    icon = Icons.Default.Help,
                    title = "Help Center",
                    onClick = onHelpCenterClick,
                    iconBackgroundColor = Blue100,
                    iconTint = Blue600
                )

                Divider()

                // About item
                SettingsItem(
                    icon = Icons.Default.Info,
                    title = "About",
                    onClick = onAboutClick,
                    iconBackgroundColor = Blue100,
                    iconTint = Blue600
                )
            }
        }
    }
}

@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit,
    iconBackgroundColor: Color,
    iconTint: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(iconBackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = iconTint,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "Navigate",
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)
        )
    }
}

// This helper class has been removed and replaced with TextButton