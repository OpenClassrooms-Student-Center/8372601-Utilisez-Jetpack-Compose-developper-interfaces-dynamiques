package com.opc.bestpodcast.data.model

data class Podcast(
    val id: String,
    val title: String,
    val description: String,
    val logoUrl: String,
    val downloadStatus: DownloadStatus = DownloadStatus.Online,
)
