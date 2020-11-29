package com.example.musicapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Track(
    @SerializedName("strTrack")
    val trackName: String,
    @SerializedName("intDuration")
    val duration: Int
) : Parcelable

data class Tracks(
    @SerializedName("track")
    val track: List<Track>
)