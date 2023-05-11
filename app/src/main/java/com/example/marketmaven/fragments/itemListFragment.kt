package com.example.marketmaven.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketmaven.R
import com.example.marketmaven.activities.CalculateTransport
import com.example.marketmaven.adapters.ItemAdapter
import com.example.marketmaven.models.ItemData

class itemListFragment : Fragment() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<ItemData>
    lateinit var imageId: Array<Int>
    lateinit var itemName: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

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

        newRecyclerView = view.findViewById(R.id.itemRecycle)
        newRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<ItemData>()
        getItemData()

        return view
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
                val bundle = Bundle()
                bundle.putString("iname", itemName[position])
                val fragment = caltransFragment()
                fragment.arguments = bundle
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, fragment, null)?.addToBackStack(null)?.commit()

            }
        })
    }

}