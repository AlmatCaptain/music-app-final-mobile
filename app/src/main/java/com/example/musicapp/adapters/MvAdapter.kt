package com.example.musicapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import com.example.musicapp.model.MusicVideo
import com.example.musicapp.networking.ApiManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.music_video_item.view.*


class MvAdapter(
    private val listItems: List<MusicVideo>? = listOf(),
    private val clickListener: (MusicVideo) -> Unit
) : RecyclerView.Adapter<MvAdapter.ItemViewHolder>() {


    class ItemViewHolder(private val view: View?) : RecyclerView.ViewHolder(view!!) {
        private val apiManager by lazy { ApiManager() }

        fun bindItem(mv: MusicVideo, clickListener: (MusicVideo) -> Unit) {
            view!!.mv_name.text = mv.trackName

            if (!mv.thumbPath.isNullOrBlank()) {
                Picasso.get()
                    .load(mv.thumbPath)
                    .fit()
                    .centerCrop()
                    .into(view.mv_image)
            } else {
                Picasso.get()
                    .load("https://lamcdn.net/lookatme.ru/post_image-image/sIaRmaFSMfrw8QJIBAa8mA-article.png")
                    .into(view.mv_image)
            }

            view.setOnClickListener { clickListener(mv) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.music_video_item,
                    parent,
                    false
                )
        )

    override fun getItemCount() = listItems!!.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(listItems!![position], clickListener)
    }

}