package com.example.musicapp.activities

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.Firebase
import com.example.musicapp.R
import com.example.musicapp.adapters.AlbumAdapter
import com.example.musicapp.adapters.MvAdapter
import com.example.musicapp.db.AppDatabase
import com.example.musicapp.model.Album
import com.example.musicapp.model.Favourites
import com.example.musicapp.networking.ApiManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val apiManager by lazy { ApiManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        card_v.visibility = View.INVISIBLE
        val searchView = search_field as SearchView
        searchView.isFocusable = false
        searchView.isIconified = false
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.toString()
                        .isNotEmpty()
                ) {
                    loadArtist(query!!)
                    loadAlbums(query)
                }
                return true
            }
        })

        fav_button.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    FavActivity::class.java
                )
            )
        }

    }

    private fun loadArtist(name: String) {
        apiManager.loadArtist(
            onSuccess = { artist ->
                val list = artist.artists
                if (!list.isNullOrEmpty()) {
                    card_v.visibility = View.VISIBLE
                    val art = list[0]
                    art_name.text = art.artistName
                    art_year.text = art.bornYear.toString()
                    art_website.text = art.website
                    art_bio.text = art.biography
                    if (!art.thumbPath.isNullOrBlank()) {
                        Picasso.get()
                            .load(art.thumbPath)
                            .into(art_image)
                    } else {
                        Picasso.get()
                            .load("https://lamcdn.net/lookatme.ru/post_image-image/sIaRmaFSMfrw8QJIBAa8mA-article.png")
                            .into(art_image)
                    }
                    if (!art.logoPath.isNullOrBlank()) {
                        Picasso.get()
                            .load(art.logoPath)
                            .into(art_logo)
                    } else {
                        Picasso.get()
                            .load("https://lamcdn.net/lookatme.ru/post_image-image/sIaRmaFSMfrw8QJIBAa8mA-article.png")
                            .into(art_image)
                    }

                    loadMvs(art.id)

                    add_fav_btn.setOnClickListener {
                        if (Firebase.auth.currentUser?.email == null) {
                            startActivity(
                                Intent(
                                    this,
                                    LoginActivity::class.java
                                )
                            )
                        } else {
                            AsyncTask.execute {
                                AppDatabase.getInstance(applicationContext)!!
                                    .getFavs()
                                    .insertFav(
                                        Favourites(
                                            email = Firebase.auth.currentUser?.email.toString(),
                                            artistId = art.id,
                                            artistName = art.artistName,
                                            bornYear = art.bornYear,
                                            genre = art.genre,
                                            website = art.website,
                                            biography = art.biography,
                                            logoPath = art.logoPath,
                                            thumbPath = art.thumbPath
                                        )
                                    )
                            }
                            Toast.makeText(this, "Added to favs list", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            },
            onError = {
                Log.d("ttah", it.message.toString())
            },
            name = name
        )
    }


    private fun itemClicked(album: Album) {
        val intent = Intent(this, AlbumActivity::class.java)
        intent.putExtra("artist", album.artist)
        intent.putExtra("album", album.albumName)
        intent.putExtra("albumId", album.id.toString())
        startActivity(intent)
    }

    private fun loadAlbums(name: String) {
        apiManager.loadAlbums(
            onSuccess = {
                if (!it.album.isNullOrEmpty()) {
                    recycler_albums.layoutManager =
                        LinearLayoutManager(
                            applicationContext,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                    recycler_albums.adapter =
                        AlbumAdapter(it.album) { album: Album ->
                            itemClicked(
                                album
                            )
                        }
                } else {
                    Toast.makeText(this, "404 not found", Toast.LENGTH_LONG)
                        .show()
                }
            },
            onError = {
                Log.d("ttah", it.message.toString())
            },
            name = name
        )
    }

    private fun loadMvs(id: Int) {
        apiManager.loadMvs(
            onSuccess = {
                if (!it.musVideos.isNullOrEmpty()) {
                    recycler_mvs.layoutManager =
                        LinearLayoutManager(
                            applicationContext,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                    recycler_mvs.adapter =
                        MvAdapter(
                            it.musVideos,
                            clickListener = { mv ->
                                val link = Intent(Intent.ACTION_VIEW, Uri.parse(mv.mvUrl))
                                startActivity(link)
                            })
                } else {
                    Toast.makeText(this, "404 not found", Toast.LENGTH_LONG)
                        .show()
                }
            },
            onError = {
                Log.d("ttah", it.message.toString())
            },
            id = id
        )
    }

}
