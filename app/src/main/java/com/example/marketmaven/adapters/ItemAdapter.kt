package com.example.marketmaven.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketmaven.R
import com.example.marketmaven.models.ItemData

// ItemAdapter class takes an ArrayList of ItemData objects and extends RecyclerView.Adapter

class ItemAdapter(private val itemList: ArrayList<ItemData>) :
    RecyclerView.Adapter<ItemAdapter.ItemHolder>() {

    // Define the interface for the item click listener
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    // Set the click listener for the adapter
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }




    // Create the ItemHolder by inflating the listitem layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listitem,
        parent, false)
        return  ItemHolder(itemView, mListener)
    }


    // Get the number of items in the adapter
    override fun getItemCount(): Int {
        return itemList.size
    }

    // Bind the data to the views in the layout
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val currentItem = itemList[position]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.itemName.text = currentItem.itemName
    }

    // Define the ItemHolder class that extends RecyclerView.ViewHolder
    class ItemHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){

        // Bind the views in the layout to the properties of the ItemHolder
        val titleImage : ImageView = itemView.findViewById(R.id.title_image)
        val itemName : TextView = itemView.findViewById(R.id.item_name)

        // Set the click listener for the itemView
        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }
}