package com.opc.bestpodcast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import com.opc.bestpodcast.ui.PodcastsScreen
import com.opc.bestpodcast.ui.theme.BestPodcastTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BestPodcastTheme {
                PodcastsScreen()
            }
        }
    }
}
