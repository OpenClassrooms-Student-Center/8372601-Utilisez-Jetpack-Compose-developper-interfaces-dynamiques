package com.opc.bestpodcast.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.opc.bestpodcast.R
import com.opc.bestpodcast.ui.theme.BestPodcastTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PodcastsScreen(
    modifier: Modifier = Modifier,
    viewModel: PodcastsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.best_podcasts),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            scope.launch {
                                listState.animateScrollToItem(0)
                            }
                        }
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .padding(innerPadding)
        ) {
            val jumpToFirstPodcastVisible by remember {
                derivedStateOf { listState.firstVisibleItemIndex > 0 }
            }
            LazyColumn(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.primaryContainer),
                contentPadding = PaddingValues(
                    top = 4.dp,
                    start = 4.dp,
                    end = 4.dp,
                    bottom = 64.dp
                ),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                state = listState
            ) {
                item {
                    PodcastSearchField(
                        searchValue = uiState.searchValue,
                        onSearchValueChanged = {
                            viewModel.onSearchValueChanged(it)
                        }
                    )
                }
                items(uiState.podcasts) { podcast ->
                    PodcastItem(
                        podcast = podcast,
                        onDownloadClicked = { viewModel.onDownloadClicked(podcast.id) },
                    )
                }
            }
            if (jumpToFirstPodcastVisible) {
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            listState.animateScrollToItem(0)
                        }
                    },
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowUpward,
                        contentDescription = stringResource(R.string.cd_jump_to_first_podcast_in_list)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewPodcastsScreen() {
    BestPodcastTheme {
        PodcastsScreen()
    }
}