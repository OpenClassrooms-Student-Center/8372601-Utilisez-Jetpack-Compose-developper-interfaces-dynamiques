package com.opc.bestpodcast.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opc.bestpodcast.R
import com.opc.bestpodcast.data.factory.PodcastFactory
import com.opc.bestpodcast.data.model.Podcast
import com.opc.bestpodcast.ui.theme.BestPodcastTheme


@Composable
fun Suggestion(
    podcast: Podcast,
    onAddToLibraryToggled: () -> Unit,
    onFavouriteToggled: () -> Unit,
    onMoreClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier =
        modifier
            .clip(MaterialTheme.shapes.medium)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.medium,
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = podcast.title,
                modifier = Modifier.weight(1f), // pour corriger le problème d'accessibilité avec un zoom à 200 %
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleLarge,
            )
            IconButton(onClick = onAddToLibraryToggled) {
                Icon(
                    imageVector = Icons.Default.AddCircleOutline,
                    contentDescription = stringResource(
                        R.string.cd_ajouter_ma_biblioth_que,
                        podcast.title
                    ),
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
        HighlightedLogo(
            logoUrl = podcast.logoUrl,
            contentDescription = null
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = podcast.category.text,
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.bodyLarge,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.weight(1f),
            )
            IconButton(onClick = onMoreClicked) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = stringResource(R.string.cd_en_savoir_plus),
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
            IconButton(onClick = onFavouriteToggled) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = stringResource(
                        R.string.cd_ajouter_aux_favoris,
                        podcast.title
                    ),
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Preview
@Composable
fun SuggestionPreview() {
    BestPodcastTheme {
        Box(modifier = Modifier.padding(8.dp)) {
            Suggestion(
                podcast = PodcastFactory.makePodcasts()[1],
                onAddToLibraryToggled = {},
                onFavouriteToggled = {},
                onMoreClicked = {},
                modifier = Modifier.width(300.dp),
            )
        }
    }
}