package com.example.musicapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import com.example.musicapp.model.Track
import com.example.musicapp.networking.ApiManager
import kotlinx.android.synthetic.main.track_item.view.*

class TrackAdapter(
    private val listItems: List<Track> = listOf()
) : RecyclerView.Adapter<TrackAdapter.ItemViewHolder>() {


    class ItemViewHolder(private val view: View?) : RecyclerView.ViewHolder(view!!) {
        private val apiManager by lazy { ApiManager() }

        @SuppressLint("SetTextI18n")
        fun bindItem(track: Track) {

            view!!.track_name.text = track.trackName
            view.duration.text = track.duration.toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.track_item, parent, false)
        )

    override fun getItemCount() = listItems.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(listItems[position])
    }

}