package com.example.musicapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import com.example.musicapp.model.Album
import com.example.musicapp.networking.ApiManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_item.view.*

class AlbumAdapter(
    private val listItems: List<Album>? = listOf(),
    private val clickListener: (Album) -> Unit
) : RecyclerView.Adapter<AlbumAdapter.ItemViewHolder>() {


    class ItemViewHolder(private val view: View?) : RecyclerView.ViewHolder(view!!) {
        private val apiManager by lazy { ApiManager() }

        @SuppressLint("SetTextI18n")
        fun bindItem(album: Album, clickListener: (Album) -> Unit) {

            view!!.album_name.text = album.albumName
            view.album_genre.text = album.genre
            view.album_year.text = album.releasedYear.toString()
            if (!album.thumbPath.isNullOrBlank()) {
                Picasso.get()
                    .load(album.thumbPath)
                    .fit()
                    .centerCrop()
                    .into(view.album_img)
            } else {
                Picasso.get()
                    .load("https://lamcdn.net/lookatme.ru/post_image-image/sIaRmaFSMfrw8QJIBAa8mA-article.png")
                    .into(view.album_img)
            }

            view.setOnClickListener { clickListener(album) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.album_item, parent, false)
        )

    override fun getItemCount() = listItems!!.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(listItems!![position], clickListener)
    }

}