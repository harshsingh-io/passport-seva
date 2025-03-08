package com.harshsinghio.passportseva.presentation.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.harshsinghio.passportseva.presentation.common.components.PassportIcon
import com.harshsinghio.passportseva.presentation.common.theme.Blue600
import com.harshsinghio.passportseva.presentation.screens.register.components.RegistrationForm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onNavigateBack: () -> Unit,
    onRegisterSuccess: () -> Unit,
    viewModel: RegisterViewModel = viewModel()
) {
    val uiState by viewModel.uiState
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Blue600),
                            contentAlignment = Alignment.Center
                        ) {
                            PassportIcon(tint = Color.White)
                        }

                        Column {
                            Text(
                                text = "Passport Seva",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                            Text(
                                text = "Ministry of External Affairs",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 12.sp
                                )
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle notification click */ }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications"
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "U",
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { /* Navigate to home */ },
                    icon = { Icon(Icons.Default.ArrowBack, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /* Navigate to services */ },
                    icon = { Icon(Icons.Default.ArrowBack, contentDescription = "Services") },
                    label = { Text("Services") }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { /* Navigate to profile */ },
                    icon = { Icon(Icons.Default.ArrowBack, contentDescription = "Profile") },
                    label = { Text("Profile") }
                )
            }
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

            Text(
                text = "User Registration",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                RegistrationForm(
                    uiState = uiState,
                    onGivenNameChange = viewModel::updateGivenName,
                    onSurnameChange = viewModel::updateSurname,
                    onDateOfBirthChange = viewModel::updateDateOfBirth,
                    onEmailChange = viewModel::updateEmail,
                    onLoginIdSameAsEmailChange = viewModel::updateLoginIdSameAsEmail,
                    onLoginIdChange = viewModel::updateLoginId,
                    onPasswordChange = viewModel::updatePassword,
                    onConfirmPasswordChange = viewModel::updateConfirmPassword,
                    onHintQuestionChange = viewModel::updateHintQuestion,
                    onHintAnswerChange = viewModel::updateHintAnswer,
                    onCaptchaChange = viewModel::updateCaptcha,
                    onPassportOfficeChange = viewModel::updatePassportOffice,
                    onCheckLoginIdAvailability = viewModel::checkLoginIdAvailability,
                    onSubmit = {
                        viewModel.register(onSuccess = onRegisterSuccess)
                    },
                    onClear = viewModel::clearForm
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    // Show error dialog if needed
    if (uiState.error.isNotEmpty()) {
        AlertDialog(
            onDismissRequest = viewModel::clearError,
            title = { Text("Error") },
            text = { Text(uiState.error) },
            confirmButton = {
                TextButton(onClick = viewModel::clearError) {
                    Text("OK")
                }
            }
        )
    }
}