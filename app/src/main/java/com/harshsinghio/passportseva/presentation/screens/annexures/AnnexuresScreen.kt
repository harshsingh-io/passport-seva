package com.harshsinghio.passportseva.presentation.screens.annexures

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.harshsinghio.passportseva.domain.model.Annexure
import com.harshsinghio.passportseva.presentation.common.theme.Blue600
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnexuresScreen(
    onNavigateBack: () -> Unit,
    viewModel: AnnexuresViewModel = viewModel()
) {
    val uiState by viewModel.uiState

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Annexures/Affidavits",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue600,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        // Main content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(uiState.annexures) { document ->
                AnnexureItem(
                    document = document,
                    onClick = { viewModel.openDocument(document) }
                )
            }
        }

        // Document viewer dialog
        if (uiState.openDocument != null) {
            DocumentViewerDialog(
                document = uiState.openDocument!!,
                currentIndex = uiState.currentDocIndex,
                totalDocuments = uiState.annexures.size,
                onClose = { viewModel.closeDocument() },
                onDownload = {
                    // Move this logic here where we have access to document and context
                    val doc = uiState.openDocument!!
                    val fileId = doc.fileUrl.substringAfter("/d/").substringBefore("/preview")
                    val downloadUrl = "https://drive.google.com/file/d/$fileId/view"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(downloadUrl))
                    context.startActivity(intent)
                },
                onNavigate = { direction -> viewModel.navigateDocument(direction) }
            )
        }
    }
}

@Composable
fun AnnexureItem(
    document: Annexure,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Blue600)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = document.title,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                        if (document.annexureCode.isNotEmpty()) {
                            Text(
                                text = "(Annexure \"${document.annexureCode}\")",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White.copy(alpha = 0.9f)
                            )
                        }
                    }

                    Row {
                        IconButton(
                            onClick = onClick,
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = Color.White
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Visibility,
                                contentDescription = "View"
                            )
                        }

                        IconButton(
                            onClick = { /* Implement download functionality */ },
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = Color.White
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Download,
                                contentDescription = "Download"
                            )
                        }
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentViewerDialog(
    document: Annexure,
    currentIndex: Int,
    totalDocuments: Int,
    onClose: () -> Unit,
    onDownload: () -> Unit,
    onNavigate: (String) -> Unit
) {
    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Dialog Header
                TopAppBar(
                    title = {
                        Column {
                            Text(
                                text = document.title,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White
                            )
                            if (document.annexureCode.isNotEmpty()) {
                                Text(
                                    text = "(Annexure \"${document.annexureCode}\")",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.9f)
                                )
                            }
                        }
                    },
                    actions = {
                        IconButton(onClick = onDownload) {
                            Icon(
                                imageVector = Icons.Default.Download,
                                contentDescription = "Download",
                                tint = Color.White
                            )
                        }
                        IconButton(onClick = onClose) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Blue600
                    )
                )

                // PDF Viewer content with WebView
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    // WebView for PDF display
                    AndroidView(
                        factory = { context ->
                            WebView(context).apply {
                                settings.javaScriptEnabled = true
                                settings.loadWithOverviewMode = true
                                settings.useWideViewPort = true
                                settings.setSupportZoom(true)
                                settings.builtInZoomControls = true
                                settings.displayZoomControls = false
                                settings.domStorageEnabled = true

                                webViewClient = WebViewClient()

                                // Use Google Docs Viewer URL format
                                val googleDocsUrl = "https://docs.google.com/gview?embedded=true&url=" +
                                        "https://drive.google.com/uc?export=download&id=${document.docId}"
                                loadUrl(googleDocsUrl)
                            }
                        },
                        update = { webView ->
                            val googleDocsUrl = "https://docs.google.com/gview?embedded=true&url=" +
                                    "https://drive.google.com/uc?export=download&id=${document.docId}"
                            webView.loadUrl(googleDocsUrl)
                        }
                    )

                    // Document counter overlay
                    Text(
                        text = "${currentIndex + 1}/${totalDocuments}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.DarkGray,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .background(Color.White.copy(alpha = 0.7f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .padding(bottom = 16.dp, end = 16.dp)
                    )

                    // Navigation buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Previous button
                        if (currentIndex > 0) {
                            FloatingActionButton(
                                onClick = { onNavigate("prev") },
                                modifier = Modifier.padding(16.dp),
                                containerColor = Color.White,
                                contentColor = Blue600,
                                elevation = FloatingActionButtonDefaults.elevation(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ChevronLeft,
                                    contentDescription = "Previous"
                                )
                            }
                        } else {
                            Spacer(modifier = Modifier.size(56.dp + 32.dp))
                        }

                        // Next button
                        if (currentIndex < totalDocuments - 1) {
                            FloatingActionButton(
                                onClick = { onNavigate("next") },
                                modifier = Modifier.padding(16.dp),
                                containerColor = Color.White,
                                contentColor = Blue600,
                                elevation = FloatingActionButtonDefaults.elevation(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ChevronRight,
                                    contentDescription = "Next"
                                )
                            }
                        } else {
                            Spacer(modifier = Modifier.size(56.dp + 32.dp))
                        }
                    }
                }
            }
        }
    }
}