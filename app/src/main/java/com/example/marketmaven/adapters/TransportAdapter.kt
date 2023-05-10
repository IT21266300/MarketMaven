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

class TransportAdapter (private val transHisList: ArrayList<TransportModel>) : RecyclerView.Adapter<TransportAdapter.ViewHolder>(){

    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.transporthislist, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentHis =    transHisList[position]
        holder.itemName.text = currentHis.transItem
        holder.itemWeight.text = currentHis.transItemWeight
        holder.itemTotalCost.text = currentHis.transTotalCost
        holder.itemDate.text = currentHis.transDate
    }


    override fun getItemCount(): Int {
        return transHisList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.transItemName)
        val itemDate: TextView = itemView.findViewById(R.id.transDate)
        val itemWeight: TextView = itemView.findViewById(R.id.transItemWeight)
        val itemTotalCost: TextView = itemView.findViewById(R.id.transTotalCost)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

}