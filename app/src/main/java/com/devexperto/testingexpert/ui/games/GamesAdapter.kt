package com.devexperto.testingexpert.ui.games

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.devexperto.testingexpert.R
import com.devexperto.testingexpert.databinding.ItemGameBinding
import com.devexperto.testingexpert.domain.VideoGame

class GamesAdapter : ListAdapter<VideoGame, GamesViewHolder>(GamesDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GamesViewHolder(view)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class GamesViewHolder(view: View) : ViewHolder(view) {

    private val binding = ItemGameBinding.bind(view)

    init {
        binding.image.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
    }

    fun bind(videoGame: VideoGame): Unit = with(binding) {
        name.text = videoGame.name
        rating.text = String.format("%.1f", videoGame.rating)
        Glide.with(image).load(videoGame.imageUrl).into(image)
        releaseDate.text = videoGame.releaseDate.toString()
    }
}

object GamesDiffCallback : androidx.recyclerview.widget.DiffUtil.ItemCallback<VideoGame>() {

    override fun areItemsTheSame(oldItem: VideoGame, newItem: VideoGame): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: VideoGame, newItem: VideoGame): Boolean {
        return oldItem == newItem
    }
}