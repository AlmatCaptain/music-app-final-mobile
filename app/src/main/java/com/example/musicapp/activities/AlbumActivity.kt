package com.example.musicapp.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.R
import com.example.musicapp.adapters.TrackAdapter
import com.example.musicapp.networking.ApiManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_album.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class AlbumActivity : AppCompatActivity() {

    private val apiManager by lazy { ApiManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        init()
    }

    private fun init() {
        loadAlbumDetail(
            intent.getStringExtra("album"),
            intent.getStringExtra("artist"),
            intent.getStringExtra("albumId")!!
                .toInt()
        )
    }

    private fun loadAlbumDetail(name: String, artist: String, albumId: Int) {
        apiManager.loadAlbumDetail(
            onSuccess = {
                if (!it.album.isNullOrEmpty()) {
                    val album = it.album[0]

                    album_name.text = album.albumName
                    artist_name.text = album.artist
                    album_desc.text = album.description
                    if (!album.thumbPath.isNullOrBlank()) {
                        Picasso.get()
                            .load(album.thumbPath)
                            .fit()
                            .centerCrop()
                            .into(album_image)
                    }
                }
            },
            onError = {
                Log.d("ttah", it.message.toString())
            },
            name = name,
            artist = artist
        )

        apiManager.loadTracks(
            onSuccess = {
                if (!it.track.isNullOrEmpty()) {
                    recycler_tracks.layoutManager =
                        LinearLayoutManager(
                            applicationContext
                        )
                    recycler_tracks.adapter =
                        TrackAdapter(it.track)
                } else {
                    Toast.makeText(this, "404 not found", Toast.LENGTH_LONG)
                        .show()
                }
            },
            onError = {
                Log.d("ttah", it.message.toString())
            },
            albumId = albumId
        )
    }
}
