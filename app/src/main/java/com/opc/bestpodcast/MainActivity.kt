package com.opc.bestpodcast

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.opc.bestpodcast.data.factory.PodcastFactory
import com.opc.bestpodcast.data.model.DownloadStatus
import com.opc.bestpodcast.ui.PodcastItem
import com.opc.bestpodcast.ui.theme.BestPodcastTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter") // TODO à supprimer quand innerPadding pris en compte
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BestPodcastTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding -> // TODO innerPadding à gérer plus tard
                    PodcastItem(
                        podcast = PodcastFactory.makePodcasts()[10].copy(downloadStatus = DownloadStatus.Online),
                        onDownloadClicked = {},
                    )
                }
            }
        }
    }
}
