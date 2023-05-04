package com.example.marketmaven.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketmaven.R
import com.example.marketmaven.model.FarmerCalModel

class farmerAdapter (private val farmerHistory: ArrayList<FarmerCalModel>) : RecyclerView.Adapter<farmerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.farmer_sell_history, parent, false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentHis =    farmerHistory[position]
        holder.itemName.text = currentHis.farmerItem
        holder.itemWeight.text = currentHis.farmerItemWeight
        holder.farmExpense.text = currentHis.edtTotalExpens
        holder.subProfit.text = String.format("%.2f", currentHis.farmerTotalProfit)

    }
    override fun getItemCount(): Int {
        return farmerHistory.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.itemName)
        val itemWeight: TextView = itemView.findViewById(R.id.itemWeight)
        val farmExpense: TextView = itemView.findViewById(R.id.farmExpense)
        val subProfit: TextView = itemView.findViewById(R.id.subProfit)

    }


}