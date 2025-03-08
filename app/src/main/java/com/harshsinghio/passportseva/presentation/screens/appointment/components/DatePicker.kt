package com.harshsinghio.passportseva.presentation.screens.appointment.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.harshsinghio.passportseva.presentation.common.util.DateTimeUtil
import com.harshsinghio.passportseva.presentation.screens.appointment.AvailableDateItem

@Composable
fun DatePicker(
    dates: List<AvailableDateItem>,
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    onNextClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Month header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Show the month and year of the first date
            Text(
                text = if (dates.isNotEmpty()) DateTimeUtil.getMonthYear(dates.first().date) else "",
                style = MaterialTheme.typography.titleMedium
            )

            // Month selector (could be implemented for a real app)
            IconButton(onClick = { /* Show month selector */ }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Select month"
                )
            }
        }

        // Date grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(dates) { dateItem ->
                DateItem(
                    dateItem = dateItem,
                    isSelected = selectedDate == dateItem.date,
                    onDateSelected = onDateSelected
                )
            }
        }

        // Next button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = onNextClick,
                enabled = selectedDate.isNotEmpty()
            ) {
                Text("Next: Select Time")
            }
        }
    }
}

@Composable
fun DateItem(
    dateItem: AvailableDateItem,
    isSelected: Boolean,
    onDateSelected: (String) -> Unit
) {
    val shape = MaterialTheme.shapes.small

    Column(
        modifier = Modifier
            .aspectRatio(1f)
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
                enabled = dateItem.isAvailable,
                onClick = { onDateSelected(dateItem.date) }
            )
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = dateItem.day,
            style = MaterialTheme.typography.bodySmall,
            color = when {
                isSelected -> MaterialTheme.colorScheme.onPrimary
                !dateItem.isAvailable -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                else -> MaterialTheme.colorScheme.onSurface
            },
            textAlign = TextAlign.Center
        )

        Text(
            text = dateItem.dayNumber,
            style = MaterialTheme.typography.titleMedium,
            color = when {
                isSelected -> MaterialTheme.colorScheme.onPrimary
                !dateItem.isAvailable -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                else -> MaterialTheme.colorScheme.onSurface
            },
            textAlign = TextAlign.Center
        )
    }
}