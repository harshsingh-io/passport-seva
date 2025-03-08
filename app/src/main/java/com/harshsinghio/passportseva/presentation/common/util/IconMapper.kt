// presentation/common/util/IconMapper.kt
package com.harshsinghio.passportseva.presentation.common.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

object IconMapper {
    fun getIconForType(iconType: String): ImageVector {
        return when (iconType.lowercase()) {
            "login" -> Icons.Default.Login
            "user_plus" -> Icons.Default.Person
            "calendar" -> Icons.Default.DateRange
            "search" -> Icons.Default.Search
            "file_text" -> Icons.Default.Description
            "credit_card" -> Icons.Default.CreditCard
            "map_pin" -> Icons.Default.LocationOn
            "bell" -> Icons.Default.Notifications
            "help_circle" -> Icons.Default.Help
            "message_square" -> Icons.Default.Message
            "settings" -> Icons.Default.Settings
            "home" -> Icons.Default.Home
            "check_circle" -> Icons.Default.CheckCircle
            "clock" -> Icons.Default.AccessTime
            "arrow_left" -> Icons.Default.ArrowBack
            "eye" -> Icons.Default.Visibility
            "eye_off" -> Icons.Default.VisibilityOff
            "chevron_down" -> Icons.Default.KeyboardArrowDown
            "chevron_right" -> Icons.Default.KeyboardArrowRight
            "person" -> Icons.Default.Person
            else -> Icons.Default.Info
        }
    }
}