package com.example.musicapp.networking

import com.example.musicapp.model.Albums
import com.example.musicapp.model.Artists
import com.example.musicapp.model.MusicVideos
import com.example.musicapp.model.Tracks
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiManager {
    fun loadArtist(
        onSuccess: (Artists) -> Unit,
        onError: (Throwable) -> Unit,
        name: String
    ) {
        ApiFactory.getClientApi()
            .getArtistByName(name)
            .enqueue(object : Callback<Artists> {

                override fun onResponse(
                    call: Call<Artists>,
                    response: Response<Artists>
                ) {
                    onSuccess(response.body()!!)
                }

                override fun onFailure(call: Call<Artists>, t: Throwable) {
                    onError(t)
                }

            })
    }

    fun loadAlbums(
        onSuccess: (Albums) -> Unit,
        onError: (Throwable) -> Unit,
        name: String
    ) {
        ApiFactory.getClientApi()
            .getAlbumsByArtistName(name)
            .enqueue(object : Callback<Albums> {
                override fun onFailure(call: Call<Albums>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<Albums>, response: Response<Albums>) {
                    onSuccess(response.body()!!)
                }
            }

            )

    }

    fun loadAlbumDetail(
        onSuccess: (Albums) -> Unit,
        onError: (Throwable) -> Unit,
        name: String,
        artist: String
    ) {
        ApiFactory.getClientApi()
            .getAlbumsByArtistNameAndName(artist, name)
            .enqueue(object : Callback<Albums> {
                override fun onFailure(call: Call<Albums>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<Albums>, response: Response<Albums>) {
                    onSuccess(response.body()!!)
                }
            }

            )

    }

    fun loadTracks(
        onSuccess: (Tracks) -> Unit,
        onError: (Throwable) -> Unit,
        albumId: Int
    ) {
        ApiFactory.getClientApi()
            .getTrackByAlbumId(albumId)
            .enqueue(object : Callback<Tracks> {
                override fun onFailure(call: Call<Tracks>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<Tracks>, response: Response<Tracks>) {
                    onSuccess(response.body()!!)
                }
            }

            )
    }

    fun loadMvs(
        onSuccess: (MusicVideos) -> Unit,
        onError: (Throwable) -> Unit,
        id: Int
    ) {
        ApiFactory.getClientApi()
            .getMvByArtistId(id)
            .enqueue(object : Callback<MusicVideos> {
                override fun onFailure(call: Call<MusicVideos>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<MusicVideos>, response: Response<MusicVideos>) {
                    onSuccess(response.body()!!)
                }
            }
            )
    }
}