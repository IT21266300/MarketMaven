package com.example.marketmaven

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketmaven.adapters.ItemAdapter
import com.example.marketmaven.models.ItemData

class ItemList : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<ItemData>
    lateinit var imageId: Array<Int>
    lateinit var itemName: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.itemdashboard)

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

        newRecyclerView.adapter = ItemAdapter(newArrayList)
    }
}