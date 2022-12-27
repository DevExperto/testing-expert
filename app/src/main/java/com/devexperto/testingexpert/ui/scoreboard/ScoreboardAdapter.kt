package com.devexperto.testingexpert.ui.scoreboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devexperto.testingexpert.R
import com.devexperto.testingexpert.databinding.ItemScoreBinding
import com.devexperto.testingexpert.domain.Score

class ScoreboardAdapter : ListAdapter<Score, ScoreViewHolder>(ScoreDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_score, parent, false)
        return ScoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ScoreViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemScoreBinding.bind(view)

    fun bind(score: Score) {
        binding.winner.text = score.winner.toString()
        binding.numberMoves.text = score.numberOfMoves.toString()
        binding.date.text = score.date.toString()
    }
}

private object ScoreDiffCallback : DiffUtil.ItemCallback<Score>() {

    override fun areItemsTheSame(oldItem: Score, newItem: Score): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Score, newItem: Score): Boolean {
        return oldItem == newItem
    }
}
