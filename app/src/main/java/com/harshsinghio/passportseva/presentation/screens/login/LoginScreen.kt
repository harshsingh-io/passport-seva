package com.harshsinghio.passportseva.presentation.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.harshsinghio.passportseva.presentation.common.components.TitleAppBar
import com.harshsinghio.passportseva.presentation.screens.login.components.LoginForm
import com.harshsinghio.passportseva.presentation.screens.login.components.SocialLoginButtons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onNavigateBack: () -> Unit,
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val loginState by viewModel.loginState
    val uiState by viewModel.uiState

    Scaffold(
        topBar = {
            TitleAppBar(
                title = "Account",
                onBackClick = onNavigateBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Passport Icon
                    PassportLogoSection()

                    // Welcome Text
                    WelcomeSection()

                    LoginForm(
                        email = uiState.email,
                        password = uiState.password,
                        showPassword = uiState.showPassword,
                        isLoading = loginState.isLoading,
                        onEmailChange = viewModel::updateEmail,
                        onPasswordChange = viewModel::updatePassword,
                        onTogglePasswordVisibility = viewModel::togglePasswordVisibility,
                        onForgotPasswordClick = { /* Handle forgot password */ },
                        onSubmit = {
                            viewModel.login {
                                onLoginSuccess()
                            }
                        }
                    )

                    // Register Button
                    TextButton(
                        onClick = onNavigateToRegister,
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text("Don't have an account? Register now")
                    }

                    Divider(
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .fillMaxWidth()
                    )

                    SocialLoginButtons(
                        onGoogleSignInClick = viewModel::loginWithGoogle,
                        onDigiLockerSignInClick = viewModel::loginWithDigiLocker
                    )
                }
            }
        }
    }

    // Show error dialog if needed
    if (loginState.error.isNotEmpty()) {
        AlertDialog(
            onDismissRequest = viewModel::clearError,
            title = { Text("Error") },
            text = { Text(loginState.error) },
            confirmButton = {
                TextButton(onClick = viewModel::clearError) {
                    Text("OK")
                }
            }
        )
    }
}

@Composable
private fun PassportLogoSection() {
    Box(
        modifier = Modifier
            .size(48.dp)
            .padding(bottom = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        com.harshsinghio.passportseva.presentation.common.components.PassportIcon(
            modifier = Modifier.size(48.dp),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun WelcomeSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 24.dp)
    ) {
        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Enter your credentials to access your account",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}