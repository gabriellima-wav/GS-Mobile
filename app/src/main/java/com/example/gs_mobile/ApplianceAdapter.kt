package com.example.prospapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gs_mobile.R
import com.example.prospapp.models.Appliance

class ApplianceAdapter(
    private val appliances: List<Appliance>,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<ApplianceAdapter.ApplianceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplianceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_appliance, parent, false)
        return ApplianceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ApplianceViewHolder, position: Int) {
        val appliance = appliances[position]
        holder.nameTextView.text = appliance.name
        holder.statusTextView.text = "Status: ${appliance.status}"
        holder.powerUsageTextView.text = "Consumo: ${appliance.powerUsage} kWh"

        holder.itemView.setOnClickListener {
            onItemClicked(position) // Chama a função ao clicar no item
        }
    }

    override fun getItemCount(): Int = appliances.size

    class ApplianceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.applianceName)
        val statusTextView: TextView = view.findViewById(R.id.applianceStatus)
        val powerUsageTextView: TextView = view.findViewById(R.id.appliancePowerUsage)
    }
}
