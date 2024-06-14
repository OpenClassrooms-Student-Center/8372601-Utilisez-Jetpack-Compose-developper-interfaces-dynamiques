package com.opc.bestpodcast.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.DownloadDone
import androidx.compose.material.icons.filled.Downloading
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

enum class DownloadStatus {
    Online,
    InProgress,
    Downloaded,
}

@Composable
fun PodcastItem(
    title: String,
    status: DownloadStatus,
) {
    Row {
        Text(
            text = title,
        )
        when (status) {
            DownloadStatus.Online ->
                Icon(
                    imageVector = Icons.Default.Cloud,
                    contentDescription = "Disponible sur le réseau",
                )

            DownloadStatus.InProgress ->
                Icon(
                    imageVector = Icons.Default.Downloading,
                    contentDescription = "Téléchargement en cours",
                )

            DownloadStatus.Downloaded ->
                Icon(
                    imageVector = Icons.Default.DownloadDone,
                    contentDescription = "Téléchargé",
                )
        }
    }
}
