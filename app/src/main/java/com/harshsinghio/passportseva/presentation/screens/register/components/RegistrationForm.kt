package com.harshsinghio.passportseva.presentation.screens.register.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.harshsinghio.passportseva.presentation.screens.register.RegisterUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationForm(
    uiState: RegisterUiState,
    onGivenNameChange: (String) -> Unit,
    onSurnameChange: (String) -> Unit,
    onDateOfBirthChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onLoginIdSameAsEmailChange: (Boolean) -> Unit,
    onLoginIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onHintQuestionChange: (String) -> Unit,
    onHintAnswerChange: (String) -> Unit,
    onCaptchaChange: (String) -> Unit,
    onPassportOfficeChange: (String) -> Unit,
    onCheckLoginIdAvailability: () -> Unit,
    onSubmit: () -> Unit,
    onClear: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }
    var showHintAnswer by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Passport Office
        DropdownField(
            label = "Passport Office",
            selectedValue = uiState.passportOffice,
            onValueChange = onPassportOfficeChange,
            options = listOf("Delhi", "Mumbai", "Chennai", "Kolkata")
        )

        // Given Name
        OutlinedTextField(
            value = uiState.givenName,
            onValueChange = onGivenNameChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Given Name") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            singleLine = true
        )

        // Surname
        OutlinedTextField(
            value = uiState.surname,
            onValueChange = onSurnameChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Surname") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            singleLine = true
        )

        // Date of Birth
        OutlinedTextField(
            value = uiState.dateOfBirth,
            onValueChange = onDateOfBirthChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Date of Birth (DD/MM/YYYY)") },
            trailingIcon = {
                IconButton(onClick = { /* Open date picker */ }) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = "Select date"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            singleLine = true
        )

        // Email
        OutlinedTextField(
            value = uiState.email,
            onValueChange = onEmailChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("E-mail Id") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            singleLine = true
        )

        // Login ID Same as Email
        Column {
            Text("Do you want your Login Id to be same as E-mail Id?")

            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = uiState.loginIdSameAsEmail,
                    onClick = { onLoginIdSameAsEmailChange(true) }
                )
                Text("Yes", modifier = Modifier.padding(start = 8.dp))

                Spacer(modifier = Modifier.width(24.dp))

                RadioButton(
                    selected = !uiState.loginIdSameAsEmail,
                    onClick = { onLoginIdSameAsEmailChange(false) }
                )
                Text("No", modifier = Modifier.padding(start = 8.dp))
            }
        }

        // Login ID
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Top
        ) {
            OutlinedTextField(
                value = uiState.loginId,
                onValueChange = onLoginIdChange,
                modifier = Modifier.weight(1f),
                label = { Text("Login Id") },
                enabled = !uiState.loginIdSameAsEmail,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
                singleLine = true,
                supportingText = {
                    if (uiState.loginIdChecked && uiState.isLoginIdAvailable) {
                        Text("Login ID is available", color = Color.Green)
                    }
                }
            )

            Button(
                onClick = onCheckLoginIdAvailability,
                enabled = !uiState.loginIdSameAsEmail && uiState.loginId.isNotEmpty() && !uiState.isCheckingAvailability,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                if (uiState.isCheckingAvailability) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Check")
                }
            }
        }

        // Password
        OutlinedTextField(
            value = uiState.password,
            onValueChange = onPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Password") },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (showPassword) "Hide password" else "Show password"
                    )
                }
            },
            singleLine = true
        )

        // "Password Policy" link
        TextButton(
            onClick = { /* Show password policy */ },
            contentPadding = PaddingValues(0.dp)
        ) {
            Text("Password Policy", color = MaterialTheme.colorScheme.primary)
        }

        // Confirm Password
        OutlinedTextField(
            value = uiState.confirmPassword,
            onValueChange = onConfirmPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Confirm Password") },
            visualTransformation = if (showConfirmPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            trailingIcon = {
                IconButton(onClick = { showConfirmPassword = !showConfirmPassword }) {
                    Icon(
                        imageVector = if (showConfirmPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (showConfirmPassword) "Hide password" else "Show password"
                    )
                }
            },
            singleLine = true
        )

        // Hint Question
        DropdownField(
            label = "Hint Question",
            selectedValue = uiState.hintQuestion,
            onValueChange = onHintQuestionChange,
            options = listOf(
                "What was your first pet's name?",
                "What was the name of your first school?",
                "In which city were you born?"
            )
        )

        // Hint Answer
        OutlinedTextField(
            value = uiState.hintAnswer,
            onValueChange = onHintAnswerChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Hint Answer") },
            visualTransformation = if (showHintAnswer) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            trailingIcon = {
                IconButton(onClick = { showHintAnswer = !showHintAnswer }) {
                    Icon(
                        imageVector = if (showHintAnswer) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (showHintAnswer) "Hide answer" else "Show answer"
                    )
                }
            },
            singleLine = true
        )

        // Change captcha picture
        TextButton(
            onClick = { /* Change captcha */ },
            contentPadding = PaddingValues(0.dp)
        ) {
            Text("Change the picture displayed", color = MaterialTheme.colorScheme.primary)
        }

        // Captcha Field
        OutlinedTextField(
            value = uiState.captcha,
            onValueChange = onCaptchaChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Enter characters displayed") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                onSubmit()
            }),
            singleLine = true
        )

        // Submit & Clear Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = onSubmit,
                modifier = Modifier.weight(1f),
                enabled = !uiState.isLoading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF6E40) // Orange color
                )
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("SUBMIT")
                }
            }

            OutlinedButton(
                onClick = onClear,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFF009688) // Teal color
                )
            ) {
                Text("CLEAR")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(
    label: String,
    selectedValue: String,
    onValueChange: (String) -> Unit,
    options: List<String>
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            value = selectedValue,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onValueChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}