package com.opc.bestpodcast.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.opc.bestpodcast.data.factory.PodcastFactory
import com.opc.bestpodcast.data.model.DownloadStatus
import com.opc.bestpodcast.data.model.Podcast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


data class PodcastsUiState(
    val podcasts: List<Podcast>,
    val searchValue: String
)

class PodcastsViewModel() : ViewModel() {

    private var podcastsInDatabase = PodcastFactory.makePodcasts()

    private val _uiState: MutableStateFlow<PodcastsUiState> = MutableStateFlow(
        PodcastsUiState(
            podcasts = podcastsInDatabase,
            searchValue = ""
        )
    )
    val uiState: StateFlow<PodcastsUiState> = _uiState.asStateFlow()

    fun onSearchValueChanged(newSearchValue: String) {
        _uiState.value = _uiState.value.copy(
            searchValue = newSearchValue,
            podcasts = podcastsInDatabase.filter { podcast ->
                podcast.title.contains(newSearchValue, ignoreCase = true)
            }
        )
    }

    fun onDownloadClicked(podcastId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            podcastsInDatabase = podcastsInDatabase.map { podcast ->
                if (podcast.id == podcastId) {
                    podcast.copy(downloadStatus = DownloadStatus.InProgress)
                } else {
                    podcast
                }
            }
            _uiState.value = _uiState.value.copy(
                podcasts = podcastsInDatabase
            )

            delay(2000)

            podcastsInDatabase = podcastsInDatabase.map { podcast ->
                if (podcast.id == podcastId) {
                    podcast.copy(downloadStatus = DownloadStatus.Downloaded)
                } else {
                    podcast
                }
            }

            _uiState.value = _uiState.value.copy(
                podcasts = podcastsInDatabase
            )
        }
    }
}