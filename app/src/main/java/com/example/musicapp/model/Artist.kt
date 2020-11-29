package com.example.musicapp.model

import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("idArtist")
    val id: Int,
    @SerializedName("strArtist")
    val artistName: String,
    @SerializedName("intBornYear")
    val bornYear: Int,
    @SerializedName("strGenre")
    val genre: String,
    @SerializedName("strWebsite")
    val website: String,
    @SerializedName("strBiographyEN")
    val biography: String,
    @SerializedName("strArtistLogo")
    val logoPath: String,
    @SerializedName("strArtistThumb")
    val thumbPath: String
)

data class Artists(
    @SerializedName("artists")
    val artists: List<Artist>
)
