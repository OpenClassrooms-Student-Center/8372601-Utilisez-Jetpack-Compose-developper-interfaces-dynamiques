package com.opc.bestpodcast.ui.suggestions


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.opc.bestpodcast.R
import com.opc.bestpodcast.data.model.Category
import com.opc.bestpodcast.data.model.Podcast
import kotlinx.coroutines.launch

// Pour simplifier le partage, dans le cadre de l'activité de la partie 4,
// tous les composants ci-dessous sont dans le même fichier. Pour une meilleure maintenabilité,
// il serait judicieux de les séparer en plusieurs fichiers.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestionsScreen(
    modifier: Modifier = Modifier,
    viewModel: SuggestionsViewModel = viewModel(),
) {
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    // 2eme partie démo
    DisposableEffect(key1 = Unit) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                // Démarrer le compteur lorsque l'écran est visible
                viewModel.startTimer()
            } else if (event == Lifecycle.Event.ON_STOP) {
                // Arrêter le compteur lorsque l'écran n'est plus visible
                viewModel.stopTimer()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            viewModel.stopTimer()
        }
    }

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Suggestions", // TODO move to strings.xml
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
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
            val listState = rememberLazyListState()
            val scope = rememberCoroutineScope()
            LaunchedEffect(key1 = viewModel.uiState.value.podcasts) {
                listState.animateScrollToItem(0)
            }

            val jumpToFirstPodcastVisible = remember {
                derivedStateOf { listState.firstVisibleItemIndex > 0 }
            }
            SuggestionsList(
                suggestions = uiState.value.podcasts,
                categories = uiState.value.categories,
                selectedCategoryId = uiState.value.selectedCategoryId,
                listState = listState,
                onCategoryClicked = { viewModel.filterByCategory(it) },
                onAddToLibraryToggled = { viewModel.togglePodcastInLibrary(it) },
                onFavouriteToggled = { viewModel.toggleFavouritePodcast(it) },
            )
            if (jumpToFirstPodcastVisible.value) {
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
                        contentDescription = "Revenir à la première suggestion" // TODO move to  strings.xml
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuggestionsList(
    suggestions: List<Podcast>,
    categories: List<Category>,
    selectedCategoryId: Int?,
    onCategoryClicked: (Int) -> Unit,
    onFavouriteToggled: (podcastId: String) -> Unit,
    onAddToLibraryToggled: (podcastId: String) -> Unit,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            top = 4.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 64.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        state = listState
    ) {
        stickyHeader {
            CategoryFilter(
                categories = categories,
                selectedId = selectedCategoryId,
                onCategoryClicked = onCategoryClicked,
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            )
        }
        items(suggestions) { podcast ->
            Suggestion(
                podcast = podcast,
                onFavouriteToggled = { onFavouriteToggled(podcast.id) },
                onAddToLibraryToggled = { onAddToLibraryToggled(podcast.id) },
                onMoreClicked = { /* TODO A compléter */ }
            )
        }
    }
}

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
            contentDescription = null,
            colorFilter =
            ColorFilter.tint(
                color = MaterialTheme.colorScheme.primary,
            ),
        )
        AsyncImage(
            model = logoUrl,
            contentDescription = contentDescription,
            placeholder = painterResource(id = R.drawable.placeholder),
            modifier =
            Modifier
                .fillMaxWidth(fraction = 0.6f)
                .aspectRatio(1f)
                .padding(8.dp),
        )
    }
}

@Composable
fun CategoryFilter(
    categories: List<Category>,
    selectedId: Int?,
    onCategoryClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Row(
        modifier =
        modifier
            .fillMaxWidth()
            .selectableGroup()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        categories.forEach { category ->
            val isSelected = (category.id == selectedId)
            Button(
                modifier =
                Modifier.selectable(
                    selected = isSelected,
                    onClick = {
                        onCategoryClicked(category.id)
                    },
                ),
                onClick = { onCategoryClicked(category.id) },
                colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                    if (isSelected) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.secondaryContainer
                    },
                    contentColor =
                    if (isSelected) {
                        MaterialTheme.colorScheme.onPrimary
                    } else {
                        MaterialTheme.colorScheme.onSecondaryContainer
                    },
                ),
            ) {
                if (isSelected) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = null,
                    )
                }

                Text(text = category.text)
            }
        }
    }
}
