package com.opc.bestpodcast.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.DownloadDone
import androidx.compose.material.icons.filled.Downloading
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                        modifier =
                            Modifier
                                .clip(CircleShape)
                                .background(Color.Yellow)
                                .border(
                                    width = 2.dp,
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = CircleShape,
                                )
                                .padding(4.dp),
                    )
                }
            DownloadStatus.InProgress ->
                Icon(
                    imageVector = Icons.Default.Downloading,
                    contentDescription = "Téléchargement en cours",
                    modifier = Modifier.padding(12.dp),
                )
            DownloadStatus.Downloaded ->
                Icon(
                    imageVector = Icons.Default.DownloadDone,
                    contentDescription = "Téléchargé",
                    modifier = Modifier.padding(12.dp),
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

@Preview
@Composable
fun PreviewPodcastItemDownloaded() {
    PodcastItem(
        podcast = PodcastFactory.makePodcasts()[0].copy(downloadStatus = DownloadStatus.Downloaded),
        onDownloadClicked = {},
    )
}

@Preview(fontScale = 2.0f)
@Composable
fun PreviewPodcastItemDownloadedLarge() {
    PodcastItem(
        podcast = PodcastFactory.makePodcasts()[0].copy(downloadStatus = DownloadStatus.Downloaded),
        onDownloadClicked = {},
    )
}
