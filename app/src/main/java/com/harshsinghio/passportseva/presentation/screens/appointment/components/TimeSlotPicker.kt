package com.harshsinghio.passportseva.presentation.screens.appointment.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.harshsinghio.passportseva.domain.model.TimeSlot
import com.harshsinghio.passportseva.presentation.common.util.DateTimeUtil

@Composable
fun TimeSlotPicker(
    selectedDate: String,
    timeSlots: List<TimeSlot>,
    selectedTimeSlot: TimeSlot?,
    onTimeSlotSelected: (TimeSlot) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Selected date display
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.CalendarToday,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = if (selectedDate.isNotEmpty())
                    DateTimeUtil.formatToFullDate(selectedDate)
                else
                    "Please select a date first",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // Time slots grid using fixed rows
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Group time slots into rows of 3
            for (row in timeSlots.chunked(3)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    for (timeSlot in row) {
                        TimeSlotItem(
                            timeSlot = timeSlot,
                            isSelected = selectedTimeSlot?.id == timeSlot.id,
                            onTimeSlotSelected = onTimeSlotSelected,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    // Fill any empty slots in the last row
                    val emptySlots = 3 - row.size
                    if (emptySlots > 0) {
                        for (i in 0 until emptySlots) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TimeSlotItem(
    timeSlot: TimeSlot,
    isSelected: Boolean,
    onTimeSlotSelected: (TimeSlot) -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = MaterialTheme.shapes.small

    Row(
        modifier = modifier
            .height(48.dp)
            .clip(shape)
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary
                else Color.Transparent
            )
            .border(
                width = 1.dp,
                color = if (isSelected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.outline,
                shape = shape
            )
            .clickable(
                enabled = timeSlot.isAvailable,
                onClick = { onTimeSlotSelected(timeSlot) }
            )
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = Icons.Default.AccessTime,
            contentDescription = null,
            tint = when {
                isSelected -> MaterialTheme.colorScheme.onPrimary
                !timeSlot.isAvailable -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                else -> MaterialTheme.colorScheme.onSurface
            },
            modifier = Modifier.size(16.dp)
        )

        Text(
            text = timeSlot.time,
            style = MaterialTheme.typography.bodySmall,
            color = when {
                isSelected -> MaterialTheme.colorScheme.onPrimary
                !timeSlot.isAvailable -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                else -> MaterialTheme.colorScheme.onSurface
            }
        )
    }
}