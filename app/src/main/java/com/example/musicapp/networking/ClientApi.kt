package com.example.musicapp.networking

import com.example.musicapp.model.Albums
import com.example.musicapp.model.Artists
import com.example.musicapp.model.MusicVideos
import com.example.musicapp.model.Tracks
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ClientApi {

    @GET("search.php")
    fun getArtistByName(@Query("s") s: String): Call<Artists>

    @GET("searchalbum.php")
    fun getAlbumsByArtistName(@Query("s") s: String): Call<Albums>

    @GET("searchalbum.php")
    fun getAlbumsByArtistNameAndName(@Query("s") s: String, @Query("a") a: String): Call<Albums>

    @GET("track.php")
    fun getTrackByAlbumId(@Query("m") m: Int): Call<Tracks>

    @GET("mvid.php")
    fun getMvByArtistId(@Query("i") i: Int): Call<MusicVideos>
}