package com.example.prospapp.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gs_mobile.R

class RankingAdapter(private val ranking: List<String>) : RecyclerView.Adapter<RankingAdapter.RankingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ranking, parent, false)
        return RankingViewHolder(view)
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        holder.bind(ranking[position])
    }

    override fun getItemCount() = ranking.size

    inner class RankingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(rank: String) {
            itemView.findViewById<TextView>(R.id.rankName).text = rank
        }
    }
}
