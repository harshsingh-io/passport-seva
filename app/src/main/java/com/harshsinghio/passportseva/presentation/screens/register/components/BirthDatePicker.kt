package com.harshsinghio.passportseva.presentation.screens.register.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthDatePicker(showDialog: Boolean, onDismiss: () -> Unit, onDateSelected: (String) -> Unit) {
    if (!showDialog) return

    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                        onClick = {
                            datePickerState.selectedDateMillis?.let { millis ->
                                val localDate =
                                        Instant.ofEpochMilli(millis)
                                                .atZone(ZoneId.systemDefault())
                                                .toLocalDate()
                                val formattedDate =
                                        localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                                onDateSelected(formattedDate)
                            }
                            onDismiss()
                        }
                ) { Text("OK") }
            },
            dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    ) {
        DatePicker(
                state = datePickerState,
                showModeToggle = false,
                title = { Text("Select your birth date") }
        )
    }
}
