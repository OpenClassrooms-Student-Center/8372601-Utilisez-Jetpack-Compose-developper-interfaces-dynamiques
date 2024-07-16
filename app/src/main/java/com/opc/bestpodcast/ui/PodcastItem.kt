package com.opc.bestpodcast.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.DownloadDone
import androidx.compose.material.icons.filled.Downloading
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.opc.bestpodcast.R
import com.opc.bestpodcast.data.factory.PodcastFactory
import com.opc.bestpodcast.data.model.DownloadStatus
import com.opc.bestpodcast.data.model.Podcast
import com.opc.bestpodcast.ui.theme.BestPodcastTheme

@Composable
fun PodcastItem(
    podcast: Podcast,
    onDownloadClicked: () -> Unit,
) {
    Row(
        modifier = Modifier.background(
            color = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.small
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                model = podcast.logoUrl,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.placeholder),
                modifier =
                    Modifier
                        .size(64.dp)
                        .clip(MaterialTheme.shapes.small),
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Ajouter ${podcast.title} à ma bibliothèque",
                    modifier =
                        Modifier
                            .size(64.dp)
                            .background(
                                brush =
                                    Brush.linearGradient(
                                        colors =
                                            listOf(
                                                MaterialTheme.colorScheme.secondary,
                                                MaterialTheme.colorScheme.primary,
                                                MaterialTheme.colorScheme.tertiaryContainer,
                                            ),
                                    ),
                                alpha = 0.9f,
                            ),
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }

        Column(
            modifier = Modifier.weight(1f), // pour corriger le problème d'accessibilité avec un zoom à 200 %
        ) {
            Text(
                text = podcast.title,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = podcast.category.text,
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.labelSmall,
                fontStyle = FontStyle.Italic,
            )
        }

        when (podcast.downloadStatus) {
            DownloadStatus.Online ->
                IconButton(onClick = onDownloadClicked) {
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = "Télécharger ${podcast.title}",
                        modifier =
                        Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = CircleShape,
                            )
                            .padding(4.dp),
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }

            DownloadStatus.InProgress ->
                Icon(
                    imageVector = Icons.Default.Downloading,
                    contentDescription = "Téléchargement en cours",
                    modifier = Modifier.padding(12.dp),
                    tint = MaterialTheme.colorScheme.primary,
                )

            DownloadStatus.Downloaded ->
                Icon(
                    imageVector = Icons.Default.DownloadDone,
                    contentDescription = "Téléchargé",
                    modifier = Modifier.padding(12.dp),
                    tint = MaterialTheme.colorScheme.primary,
                )
        }
    }
}

@Preview
@Composable
fun PreviewPodcastItemOnline() {
    BestPodcastTheme {
        PodcastItem(
            podcast = PodcastFactory.makePodcasts()[0].copy(downloadStatus = DownloadStatus.Online),
            onDownloadClicked = {},
        )
    }
}

@Preview
@Composable
fun PreviewPodcastItemInProgress() {
    BestPodcastTheme {
        PodcastItem(
            podcast = PodcastFactory.makePodcasts()[0].copy(downloadStatus = DownloadStatus.InProgress),
            onDownloadClicked = {},
        )
    }
}

@Preview
@Composable
fun PreviewPodcastItemDownloaded() {
    BestPodcastTheme {
        PodcastItem(
            podcast = PodcastFactory.makePodcasts()[0].copy(downloadStatus = DownloadStatus.Downloaded),
            onDownloadClicked = {},
        )
    }
}

@Preview(fontScale = 2.0f)
@Composable
fun PreviewPodcastItemDownloadedLarge() {
    BestPodcastTheme {
        PodcastItem(
            podcast = PodcastFactory.makePodcasts()[0].copy(downloadStatus = DownloadStatus.Downloaded),
            onDownloadClicked = {},
        )
    }
}
