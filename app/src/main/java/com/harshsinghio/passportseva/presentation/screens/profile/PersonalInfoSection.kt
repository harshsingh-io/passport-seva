package com.harshsinghio.passportseva.presentation.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import com.harshsinghio.passportseva.presentation.common.components.TitleAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.harshsinghio.passportseva.domain.model.Address
import com.harshsinghio.passportseva.domain.model.Gender
import com.harshsinghio.passportseva.domain.model.User
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Section for editing personal information
 *
 * @param userData The user data to display and edit
 * @param onBack Callback to navigate back to the profile screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalInfoSection(
    userData: User,
    onBack: () -> Unit
) {
    val scrollState = rememberScrollState()

    var name by remember { mutableStateOf(userData.name) }
    var email by remember { mutableStateOf(userData.email) }
    var mobile by remember { mutableStateOf(userData.mobile) }
    var dateOfBirth by remember {
        mutableStateOf(userData.dateOfBirth?.toString() ?: "")
    }
    var gender by remember { mutableStateOf(userData.gender?.name ?: "") }

    // Address fields
    var street by remember { mutableStateOf(userData.address?.street ?: "") }
    var city by remember { mutableStateOf(userData.address?.city ?: "") }
    var state by remember { mutableStateOf(userData.address?.state ?: "") }
    var pincode by remember { mutableStateOf(userData.address?.pincode ?: "") }
    var country by remember { mutableStateOf(userData.address?.country ?: "India") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        // Use TitleAppBar instead of custom header
        TitleAppBar(
            title = "Personal Information",
            onBackClick = onBack
        )

        // Personal Information Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Full Name
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Mobile Number
                OutlinedTextField(
                    value = mobile,
                    onValueChange = { mobile = it },
                    label = { Text("Mobile Number") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Date of Birth and Gender in a row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Date of Birth
                    OutlinedTextField(
                        value = dateOfBirth,
                        onValueChange = { dateOfBirth = it },
                        label = { Text("Date of Birth") },
                        modifier = Modifier.weight(1f)
                    )

                    // Gender dropdown
                    ExposedDropdownMenuBox(
                        expanded = false,
                        onExpandedChange = { /* Handle expansion */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        OutlinedTextField(
                            value = when (gender) {
                                "MALE" -> "Male"
                                "FEMALE" -> "Female"
                                "OTHER" -> "Other"
                                "PREFER_NOT_TO_SAY" -> "Prefer not to say"
                                else -> ""
                            },
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Gender") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = false)
                            },
                            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = false,
                            onDismissRequest = { /* Handle dismiss */ }
                        ) {
                            // Gender options
                            val options = listOf("Male", "Female", "Other", "Prefer not to say")
                            options.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        gender = when (option) {
                                            "Male" -> "MALE"
                                            "Female" -> "FEMALE"
                                            "Other" -> "OTHER"
                                            "Prefer not to say" -> "PREFER_NOT_TO_SAY"
                                            else -> ""
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        // Address Information Card
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Address Information",
                    style = MaterialTheme.typography.titleMedium
                )

                // Street Address
                OutlinedTextField(
                    value = street,
                    onValueChange = { street = it },
                    label = { Text("Street Address") },
                    modifier = Modifier.fillMaxWidth()
                )

                // City and State in a row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // City
                    OutlinedTextField(
                        value = city,
                        onValueChange = { city = it },
                        label = { Text("City") },
                        modifier = Modifier.weight(1f)
                    )

                    // State
                    OutlinedTextField(
                        value = state,
                        onValueChange = { state = it },
                        label = { Text("State") },
                        modifier = Modifier.weight(1f)
                    )
                }

                // PIN Code and Country in a row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // PIN Code
                    OutlinedTextField(
                        value = pincode,
                        onValueChange = { pincode = it },
                        label = { Text("PIN Code") },
                        modifier = Modifier.weight(1f)
                    )

                    // Country
                    OutlinedTextField(
                        value = country,
                        onValueChange = { country = it },
                        label = { Text("Country") },
                        modifier = Modifier.weight(1f)
                    )
                }

                // Save button
                Button(
                    onClick = {
                        // Save user data and go back
                        // This would update the user profile in a real app
                        onBack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = "Save"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Save Changes")
                }
            }
        }
    }
}