package com.example.prospapp.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gs_mobile.R

class BadgesAdapter(private val badges: List<String>) : RecyclerView.Adapter<BadgesAdapter.BadgeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BadgeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_badge, parent, false)
        return BadgeViewHolder(view)
    }

    override fun onBindViewHolder(holder: BadgeViewHolder, position: Int) {
        holder.bind(badges[position])
    }

    override fun getItemCount() = badges.size

    inner class BadgeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(badge: String) {
            itemView.findViewById<TextView>(R.id.badgeName).text = badge
        }
    }
}
