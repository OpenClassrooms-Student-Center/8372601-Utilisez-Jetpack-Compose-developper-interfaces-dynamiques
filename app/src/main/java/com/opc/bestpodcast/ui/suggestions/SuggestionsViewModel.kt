package com.opc.bestpodcast.ui.suggestions

import android.util.Log
import androidx.lifecycle.ViewModel
import com.opc.bestpodcast.data.factory.PodcastFactory
import com.opc.bestpodcast.data.model.Category
import com.opc.bestpodcast.data.model.Podcast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SuggestionsUiState(
    val podcasts: List<Podcast>,
    val categories: List<Category>,
    val selectedCategoryId: Int? = null,
)


class SuggestionsViewModel : ViewModel() {
    private val podcasts = PodcastFactory.makePodcasts()
    private val categories = podcasts.map { it.category }.distinct()

    private val _uiState = MutableStateFlow(
        SuggestionsUiState(
            podcasts = podcasts,
            categories = categories
        )
    )
    val uiState: StateFlow<SuggestionsUiState> = _uiState.asStateFlow()

    fun filterByCategory(categoryId: Int) {
        if (_uiState.value.selectedCategoryId == categoryId) {
            _uiState.value = SuggestionsUiState(
                podcasts = PodcastFactory.makePodcasts(),
                categories = categories,
                selectedCategoryId = null
            )
        } else {
            _uiState.value = SuggestionsUiState(
                podcasts = PodcastFactory.makePodcasts().filter { it.category.id == categoryId },
                categories = categories,
                selectedCategoryId = categoryId
            )
        }
    }


    fun togglePodcastInLibrary(podcastId: String) {
        /*TODO */
    }

    fun toggleFavouritePodcast(podcastId: String) {
        /* TODO */
    }

    fun startTimer() {
        // TODO appeler ici l'outil d'analytics
        Log.d("BestPodcast", "START viewing SuggestionsScreen")
    }

    fun stopTimer() {
        // TODO appeler ici l'outil d'analytics
        Log.d("BestPodcast", "STOP viewing SuggestionsScreen")
    }
}