package com.example.musicapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(
    @SerializedName("idAlbum")
    val id: Int,
    @SerializedName("strAlbum")
    val albumName: String,
    @SerializedName("intYearReleased")
    val releasedYear: Int,
    @SerializedName("strGenre")
    val genre: String,
    @SerializedName("strDescriptionEN")
    val description: String,
    @SerializedName("strArtist")
    val artist: String,
    @SerializedName("strAlbumThumb")
    val thumbPath: String
) : Parcelable

data class Albums(
    @SerializedName("album")
    val album: List<Album>
)