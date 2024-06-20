package com.opc.bestpodcast.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.DownloadDone
import androidx.compose.material.icons.filled.Downloading
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.opc.bestpodcast.data.factory.PodcastFactory
import com.opc.bestpodcast.data.model.DownloadStatus
import com.opc.bestpodcast.data.model.Podcast

@Composable
fun PodcastItem(
    podcast: Podcast,
    onDownloadClicked: () -> Unit,
) {
    Row {
        Text(
            text = podcast.title,
            modifier = Modifier.weight(1f), // pour corriger le problème d'accessibilité avec un zoom à 200 %
        )
        when (podcast.downloadStatus) {
            DownloadStatus.Online ->
                IconButton(onClick = onDownloadClicked) {
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = "Télécharger ${podcast.title}",
                    )
                }
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

@Preview
@Composable
fun PreviewPodcastItemOnline() {
    PodcastItem(
        podcast = PodcastFactory.makePodcasts()[0].copy(downloadStatus = DownloadStatus.Online),
        onDownloadClicked = {},
    )
}

@Preview
@Composable
fun PreviewPodcastItemInProgress() {
    PodcastItem(
        podcast = PodcastFactory.makePodcasts()[0].copy(downloadStatus = DownloadStatus.InProgress),
        onDownloadClicked = {},
    )
}

@Preview(fontScale = 2.0f)
@Composable
fun PreviewPodcastItemDownloaded() {
    PodcastItem(
        podcast = PodcastFactory.makePodcasts()[0].copy(downloadStatus = DownloadStatus.Downloaded),
        onDownloadClicked = {},
    )
}
