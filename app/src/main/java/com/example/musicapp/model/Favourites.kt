package com.example.musicapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favs")
data class Favourites(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "artistId")
    @SerializedName("idArtist")
    val artistId: Int,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "artName")
    @SerializedName("strArtist")
    val artistName: String,
    @ColumnInfo(name = "bornYear")
    @SerializedName("intBornYear")
    val bornYear: Int,
    @ColumnInfo(name = "genre")
    @SerializedName("strGenre")
    val genre: String,
    @ColumnInfo(name = "website")
    @SerializedName("strWebsite")
    val website: String,
    @ColumnInfo(name = "biography")
    @SerializedName("strBiographyEN")
    val biography: String,
    @ColumnInfo(name = "logo")
    @SerializedName("strArtistLogo")
    val logoPath: String,
    @ColumnInfo(name = "thumb")
    @SerializedName("strArtistThumb")
    val thumbPath: String
)