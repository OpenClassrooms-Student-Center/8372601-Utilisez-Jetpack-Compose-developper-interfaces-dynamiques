package com.opc.bestpodcast.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.opc.bestpodcast.ui.podcasts.PodcastsScreen
import com.opc.bestpodcast.ui.suggestions.SuggestionsScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "podcasts"
    ) {
        composable("podcasts") {
            PodcastsScreen(
                navigateToSuggestions = {
                    navController.navigate("suggestions")
                }
            )
        }
        composable("suggestions") { SuggestionsScreen() }
    }

}