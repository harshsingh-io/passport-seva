package com.harshsinghio.passportseva.presentation.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.harshsinghio.passportseva.R

@Composable
fun SocialLoginButtons(
    onGoogleSignInClick: () -> Unit,
    onDigiLockerSignInClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Or continue with",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Google Sign In Button
            SocialButton(
                onClick = onGoogleSignInClick,
                icon = painterResource(id = R.drawable.ic_google),
                text = "Google",
                modifier = Modifier.weight(1f)
            )

            // DigiLocker Sign In Button
            SocialButton(
                onClick = onDigiLockerSignInClick,
                icon = painterResource(id = R.drawable.ic_digilocker),
                text = "DigiLocker",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun SocialButton(
    onClick: () -> Unit,
    icon: Painter,
    text: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        border = ButtonDefaults.outlinedButtonBorder
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = icon,
                contentDescription = "$text logo",
                modifier = Modifier.size(20.dp)
            )
            Text(text = text)
        }
    }
}