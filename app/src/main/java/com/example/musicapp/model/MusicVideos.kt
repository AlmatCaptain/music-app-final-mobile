package com.example.musicapp.model

import com.google.gson.annotations.SerializedName

data class MusicVideo(
    @SerializedName("idArtist")
    val id: Int,
    @SerializedName("strTrack")
    val trackName: String,
    @SerializedName("strMusicVid")
    val mvUrl: String,
    @SerializedName("strTrackThumb")
    val thumbPath: String
)

data class MusicVideos(
    @SerializedName("mvids")
    val musVideos: List<MusicVideo>
)