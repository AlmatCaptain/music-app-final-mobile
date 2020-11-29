package com.example.musicapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import com.example.musicapp.model.Favourites
import com.example.musicapp.networking.ApiManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.artist_info_card.view.*

class FavAdapter(
    private val listItems: List<Favourites>? = listOf()
) : RecyclerView.Adapter<FavAdapter.ItemViewHolder>() {


    class ItemViewHolder(private val view: View?) : RecyclerView.ViewHolder(view!!) {
        private val apiManager by lazy { ApiManager() }

        fun bindItem(favourites: Favourites) {
            view!!.art_name.text = favourites.artistName
            view.art_year.text = favourites.bornYear.toString()
            view.art_website.text = favourites.website
            view.art_bio.text = favourites.biography
            if (!favourites.thumbPath.isNullOrBlank()) {
                Picasso.get()
                    .load(favourites.thumbPath)
                    .into(view.art_image)
            } else {
                Picasso.get()
                    .load("https://lamcdn.net/lookatme.ru/post_image-image/sIaRmaFSMfrw8QJIBAa8mA-article.png")
                    .into(view.art_image)
            }
            if (!favourites.logoPath.isNullOrBlank()) {
                Picasso.get()
                    .load(favourites.logoPath)
                    .into(view.art_logo)
            } else {
                Picasso.get()
                    .load("https://lamcdn.net/lookatme.ru/post_image-image/sIaRmaFSMfrw8QJIBAa8mA-article.png")
                    .into(view.art_image)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.artist_info_card,
                    parent,
                    false
                )
        )

    override fun getItemCount() = listItems!!.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(listItems!![position])
    }

}