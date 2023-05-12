package com.example.marketmaven.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketmaven.R
import com.example.marketmaven.adapters.ItemAdapter
import com.example.marketmaven.models.ItemData


class ItemListFarmer : AppCompatActivity() {

    // Declare variables for the RecyclerView, ArrayList, image IDs, and item names.
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<ItemData>
    lateinit var imageId: Array<Int>
    lateinit var itemName: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.itemdashboard_farm)
// Initialize the image IDs and item names arrays
        imageId = arrayOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d
        )
//names of veg and flutes
        itemName = arrayOf(
            "Avocado",
            "Banana",
            "Beans",
            "Cabbage"
        )

        // Find the RecyclerView in the layout and set its layout manager and fixed size.
        newRecyclerView = findViewById(R.id.itemRecycle)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        // Initialize the ArrayList and call the getItemData() method to populate it.
        newArrayList = arrayListOf<ItemData>()
        getItemData()
    }

    // This method populates the ArrayList with data from the image ID and item name arrays.
    private fun getItemData() {
        for(i in imageId.indices){
            val item = ItemData(imageId[i], itemName[i])
            newArrayList.add(item)
        }
// Create a new adapter with the ArrayList and set it to the RecyclerView.
        var adapter = ItemAdapter(newArrayList)
        newRecyclerView.adapter = adapter

        // Set an item click listener for the adapter to launch the FarmerCalSetValues activity.
        adapter.setOnItemClickListener(object : ItemAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

                val intent = Intent(this@ItemListFarmer, FarmerCalSetValues::class.java)
                intent.putExtra("iname", itemName[position])
                startActivity(intent)
            }


        })
    }
}