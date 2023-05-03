package com.example.marketmaven.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketmaven.R
import com.example.marketmaven.models.TransportModel
import org.w3c.dom.Text

class TransportAdapter (private val transHisList: ArrayList<TransportModel>) : RecyclerView.Adapter<TransportAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.transporthislist, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentHis =    transHisList[position]
        holder.itemName.text = currentHis.transItem
        holder.itemWeight.text = currentHis.transItemWeight
        holder.itemPickup.text = currentHis.transPickUp
        holder.itemDelivery.text = currentHis.transDelivery
        holder.itemDistance.text = currentHis.transDistance
        holder.itemFuelEfficient.text = currentHis.transFuelEfficient
        holder.itemTotalFuelEfficient.text = String.format("%.2f", currentHis.transTotalFuelEfficient)
        holder.itemFuelPrice.text = String.format("%.2f", currentHis.transFuelPrice)
        holder.itemTotalFuelCost.text = String.format("%.2f", currentHis.transTotalFuelCost)
        holder.itemDriverWage.text = String.format("%.2f", currentHis.transDriverWage)
        holder.itemTotalCost.text = String.format("%.2f", currentHis.transTotalCost)
    }

    override fun getItemCount(): Int {
        return transHisList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.transItemName)
        val itemWeight: TextView = itemView.findViewById(R.id.transItemWeight)
        val itemPickup: TextView = itemView.findViewById(R.id.transPickUp)
        val itemDelivery: TextView = itemView.findViewById(R.id.transDelivery)
        val itemDistance: TextView = itemView.findViewById(R.id.transDistance)
        val itemFuelEfficient: TextView = itemView.findViewById(R.id.transFuelEfficient)
        val itemTotalFuelEfficient: TextView = itemView.findViewById(R.id.transTotalFuelEfficient)
        val itemFuelPrice: TextView = itemView.findViewById(R.id.transFuelPrice)
        val itemTotalFuelCost: TextView = itemView.findViewById(R.id.transTotalFuelCost)
        val itemDriverWage: TextView = itemView.findViewById(R.id.transDriverWag)
        val itemTotalCost: TextView = itemView.findViewById(R.id.transTotalCost)
    }

}