package com.example.marketmaven.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketmaven.R
import com.example.marketmaven.adapters.ItemAdapter
import com.example.marketmaven.models.ItemData

class ItemList : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<ItemData>
    lateinit var imageId: Array<Int>
    lateinit var itemName: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.itemdashboard_farm)

        imageId = arrayOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d
        )

        itemName = arrayOf(
            "Avacado",
            "Banana",
            "Beans",
            "Cabage"
        )

        newRecyclerView = findViewById(R.id.itemRecycle)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<ItemData>()
        getItemData()
    }

    private fun getItemData() {
        for(i in imageId.indices){
            val item = ItemData(imageId[i], itemName[i])
            newArrayList.add(item)
        }

        var adapter = ItemAdapter(newArrayList)
        newRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : ItemAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
//                Toast.makeText(this@ItemList, "Clicked on item no: $position", Toast.LENGTH_LONG).show()
                val intent = Intent(this@ItemList, FarmerCalSetValues::class.java)
                intent.putExtra("iname", itemName[position])
                startActivity(intent)
            }


        })
    }
}