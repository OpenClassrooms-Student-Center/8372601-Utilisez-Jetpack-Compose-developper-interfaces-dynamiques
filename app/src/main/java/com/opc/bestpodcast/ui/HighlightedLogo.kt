package com.opc.bestpodcast.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.opc.bestpodcast.R

@Composable
fun HighlightedLogo(
    logoUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(id = R.drawable.soundwave),
            contentDescription = contentDescription,
            colorFilter =
                ColorFilter.tint(
                    color = MaterialTheme.colorScheme.primary,
                ),
        )
        AsyncImage(
            model = logoUrl,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.placeholder),
            modifier =
                Modifier
                    .fillMaxWidth(fraction = 0.6f)
                    .aspectRatio(1f)
                    .padding(8.dp),
        )
    }
}
