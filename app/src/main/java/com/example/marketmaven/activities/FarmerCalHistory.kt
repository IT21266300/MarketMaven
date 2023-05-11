package com.example.marketmaven.activities

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

        farmerHisRecycle = findViewById(R.id.farmerHisRecycle)
        farmerHisRecycle.layoutManager = LinearLayoutManager(this)
        farmerHisRecycle.setHasFixedSize(true)
        txtLoading = findViewById(R.id.loadingHis)

        farmerHisList = arrayListOf<FarmerCalModel>()

        getFarmerHistory()

    }

    private fun  getFarmerHistory(){
        farmerHisRecycle.visibility = View.GONE
        txtLoading.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Farmer")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                farmerHisList.clear()
                if(snapshot.exists()){
                    for (transSnap in snapshot.children){
                        val farmerData =  transSnap.getValue(FarmerCalModel::class.java)
                        farmerHisList.add(farmerData!!)
                    }
                    val farmerHisAdapter = farmerAdapter(farmerHisList)
                    farmerHisRecycle.adapter = farmerHisAdapter

                    farmerHisAdapter.setOnItemClickListener(object : farmerHisAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@TransportHistory, TransportHisItem::class.java)

                            //put extras
                            intent.putExtra("transId", transHisList[position].transId)
                            intent.putExtra("transItem", transHisList[position].transItem)
                            intent.putExtra("transDate", transHisList[position].transDate)
                            intent.putExtra("transWeight", transHisList[position].transItemWeight)
                            intent.putExtra("transWeightFactor", transHisList[position].transWeightFactor)
                            intent.putExtra("transTotalWeightFactor", transHisList[position].transTotalWeightFactor)
                            intent.putExtra("transPickUp", transHisList[position].transPickUp)
                            intent.putExtra("transDelivery", transHisList[position].transDelivery)
                            intent.putExtra("transDistance", transHisList[position].transDistance)
                            intent.putExtra("transFuelEfficient", transHisList[position].transFuelEfficient)
                            intent.putExtra("transTotalFuelEfficient", transHisList[position].transTotalFuelEfficient)
                            intent.putExtra("transFuelPrice", transHisList[position].transFuelPrice)
                            intent.putExtra("transTotalFuelCost", transHisList[position].transTotalFuelCost)
                            intent.putExtra("transDriverWage", transHisList[position].transDriverWage)
                            intent.putExtra("transTotalCost", transHisList[position].transTotalCost)


                            startActivity(intent)
                        }


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