package com.harshsinghio.passportseva.presentation.common.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harshsinghio.passportseva.R

/**
 * Enhanced bottom navigation bar for the Passport Seva application with custom SVG icons
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
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(horizontal = 12.dp),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.9f))
                .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Home Tab
            NavItemWithSvg(
                filledIconRes = R.drawable.home_filled,
                outlineIconRes = R.drawable.home_stroke,
                label = "Home",
                isSelected = currentRoute == "home",
                onClick = onHomeClick
            )

            // Services Tab
            NavItemWithSvg(
                filledIconRes = R.drawable.services_filled,
                outlineIconRes = R.drawable.services_stroke,
                label = "Services",
                isSelected = currentRoute == "services",
                onClick = onServicesClick
            )

            // Profile Tab
            NavItemWithSvg(
                filledIconRes = R.drawable.profile_filled,
                outlineIconRes = R.drawable.profile_stroke,
                label = "Profile",
                isSelected = currentRoute == "profile",
                onClick = onProfileClick
            )
        }
    }
}

@Composable
private fun RowScope.NavItemWithSvg(
    filledIconRes: Int,
    outlineIconRes: Int,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
                .background(if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent)
                .customClickable(onClick = onClick),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Use the appropriate icon based on selection state
            val iconRes = if (isSelected) filledIconRes else outlineIconRes

            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                tint = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = label,
                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

// Custom clickable modifier with ripple effect
@Composable
private fun Modifier.customClickable(
    onClick: () -> Unit
): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    return this.then(
        clickable(
            interactionSource = interactionSource,
            indication = rememberRipple(bounded = true),
            onClick = onClick
        )
    )
}