package com.example.marketmaven.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketmaven.R
import com.example.marketmaven.models.ItemData

class ItemAdapter(private val itemList: ArrayList<ItemData>) :
    RecyclerView.Adapter<ItemAdapter.ItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listitem,
        parent, false)
        return  ItemHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val currentItem = itemList[position]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.itemName.text = currentItem.itemName
    }

    class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val titleImage : ImageView = itemView.findViewById(R.id.title_image)
        val itemName : TextView = itemView.findViewById(R.id.item_name)

    }
}