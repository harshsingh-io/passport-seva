package com.harshsinghio.passportseva.presentation.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

/**
 * A simple loading indicator component
 *
 * @param modifier Modifier to be applied to the loading indicator
 * @param size The size of the loading indicator
 */
@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    size: Float = 48f
) {
    CircularProgressIndicator(
        modifier = modifier.size(size.dp),
        color = MaterialTheme.colorScheme.primary,
        strokeWidth = 4.dp
    )
}

/**
 * A full-screen loading indicator dialog
 *
 * @param isVisible Whether the loading dialog is visible
 * @param onDismissRequest Callback for when the dialog is dismissed
 */
@Composable
fun LoadingDialog(
    isVisible: Boolean,
    onDismissRequest: () -> Unit = {}
) {
    if (isVisible) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Surface(
                modifier = Modifier.size(100.dp),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surface
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    LoadingIndicator()
                }
            }
        }
    }
}