package com.example.marketmaven.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketmaven.R

import com.example.marketmaven.adapters.farmerAdapter
import com.example.marketmaven.model.FarmerCalModel

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FarmerCalHistory : AppCompatActivity() {

    private lateinit var farmerHisRecycle: RecyclerView
    private lateinit var  txtLoading: TextView
    private lateinit var farmerHisList: ArrayList<FarmerCalModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.farmer_historypage)

        // Find the RecyclerView from the layout and set its layout manager and fixed size
        farmerHisRecycle = findViewById(R.id.farmerHisRecycle)
        farmerHisRecycle.layoutManager = LinearLayoutManager(this)
        farmerHisRecycle.setHasFixedSize(true)
        txtLoading = findViewById(R.id.loadingHis)

        farmerHisList = arrayListOf<FarmerCalModel>()

        getFarmerHistory()

    }

    // Function to retrieve the data and populate the RecyclerView
    private fun  getFarmerHistory(){
        // Hide the RecyclerView and show the loading TextView
        farmerHisRecycle.visibility = View.GONE
        txtLoading.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Farmer")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                farmerHisList.clear()
                if(snapshot.exists()){
                    for (farmerSnap in snapshot.children){
                        val farmerData =  farmerSnap.getValue(FarmerCalModel::class.java)
                        farmerHisList.add(farmerData!!)
                    }
                    val farmerHisAdapter = farmerAdapter(farmerHisList)
                    farmerHisRecycle.adapter = farmerHisAdapter

                    farmerHisAdapter.setOnItemClickListener(object : farmerAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
// Create an intent to go to the FarmerDetailHistory activity
                            val intent = Intent(this@FarmerCalHistory, FarmerDetailHistory::class.java)

                            //put extras
                            intent.putExtra("farmerId", farmerHisList[position].farmerId)
                            intent.putExtra("farmerItem", farmerHisList[position].farmerItem)
                            intent.putExtra("farmerItemWeight", farmerHisList[position].farmerItemWeight)
                            intent.putExtra("calDate", farmerHisList[position].calDate)
                            intent.putExtra("edtTotalExpens", farmerHisList[position].edtTotalExpens)
                            intent.putExtra("farmerTotalProfit", farmerHisList[position].farmerTotalProfit)
                            // Start the activity
                            startActivity(intent)
                        }
                    })

// Show the RecyclerView and hide the loading TextView
                        farmerHisRecycle.visibility = View.VISIBLE
                    txtLoading.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}