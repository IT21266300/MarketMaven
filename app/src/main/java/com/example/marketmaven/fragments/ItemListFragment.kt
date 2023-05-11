package com.example.marketmaven.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketmaven.R
import com.example.marketmaven.adapters.ItemAdapter
import com.example.marketmaven.models.ItemData

class ItemListFragment : Fragment() {

    // declare views, data structures, and variables
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<ItemData>
    lateinit var imageId: Array<Int>
    lateinit var itemName: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // initialize and populate arrays with image and item name data
        imageId = arrayOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.i,
            R.drawable.j,
            R.drawable.k,
            R.drawable.l,
            )

        itemName = arrayOf(
            "Avocado",
            "Banana",
            "Beans",
            "Cabbage",
            "Corn",
            "Bell Pepper",
            "Graphs",
            "Guava",
            "Onion",
            "Papaya",
            "Potato",
            "Tomato"
        )

        // initialize the recycler view and set its layout manager and fixed size
        newRecyclerView = view.findViewById(R.id.itemRecycle)
        newRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        newRecyclerView.setHasFixedSize(true)

        // create a new array list of ItemData objects and populate it with data from imageId and itemName arrays
        newArrayList = arrayListOf<ItemData>()
        getItemData()

        return view
    }

    // populate newArrayList with data from imageId and itemName arrays
    private fun getItemData() {
        for(i in imageId.indices){
            val item = ItemData(imageId[i], itemName[i])
            newArrayList.add(item)
        }

        // initialize an ItemAdapter object with newArrayList data and set the adapter to the recycler view
        var adapter = ItemAdapter(newArrayList)
        newRecyclerView.adapter = adapter

        // set an onItemClickListener for the adapter and pass a bundle to CaltransFragment when an item is clicked
        adapter.setOnItemClickListener(object : ItemAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val bundle = Bundle()
                bundle.putString("iname", itemName[position])
                val fragment = CaltransFragment()
                fragment.arguments = bundle
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, fragment, null)?.addToBackStack(null)?.commit()

            }
        })
    }

}