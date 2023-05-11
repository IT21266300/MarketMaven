package com.example.marketmaven.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.marketmaven.R
import com.example.marketmaven.models.TransportModel
import org.w3c.dom.Text

// Define the TransportAdapter class which extends the RecyclerView.Adapter class
class TransportAdapter (private val transHisList: ArrayList<TransportModel>) : RecyclerView.Adapter<TransportAdapter.ViewHolder>(){

    // Define an interface to handle click events on items in the RecyclerView
    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    // Define a method to set the listener for click events
    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    // Inflate the layout for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.transporthislist, parent, false)
        return ViewHolder(itemView, mListener)
    }

    // Bind data to the view for each item in the RecyclerView
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentHis =    transHisList[position]
        holder.itemName.text = currentHis.transItem
        holder.itemWeight.text = currentHis.transItemWeight
        holder.itemTotalCost.text = currentHis.transTotalCost
        holder.itemDate.text = currentHis.transDate
    }


    // Return the number of items in the RecyclerView
    override fun getItemCount(): Int {
        return transHisList.size
    }

    // Define a ViewHolder class which holds the views for each item in the RecyclerView
    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.transItemName)
        val itemDate: TextView = itemView.findViewById(R.id.transDate)
        val itemWeight: TextView = itemView.findViewById(R.id.transItemWeight)
        val itemTotalCost: TextView = itemView.findViewById(R.id.transTotalCost)

        // Add a click listener to the itemView
        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

}