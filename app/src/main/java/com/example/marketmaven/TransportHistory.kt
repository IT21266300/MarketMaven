package com.example.marketmaven

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketmaven.adapters.TransportAdapter
import com.example.marketmaven.models.TransportModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TransportHistory : AppCompatActivity() {

    private lateinit var transHisRecycle: RecyclerView
    private lateinit var  txtLoading: TextView
    private lateinit var transHisList: ArrayList<TransportModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.historypage)

        transHisRecycle = findViewById(R.id.transHisRecycle)
        transHisRecycle.layoutManager = LinearLayoutManager(this)
        transHisRecycle.setHasFixedSize(true)
        txtLoading = findViewById(R.id.loadingHis)

        transHisList = arrayListOf<TransportModel>()

        getTransportHistory()
    }

    private fun  getTransportHistory(){
        transHisRecycle.visibility = View.GONE
        txtLoading.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Transport")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                transHisList.clear()
                if(snapshot.exists()){
                    for (transSnap in snapshot.children){
                        val transData =  transSnap.getValue(TransportModel::class.java)
                        transHisList.add(transData!!)
                    }
                    val transHisAdapter = TransportAdapter(transHisList)
                    transHisRecycle.adapter = transHisAdapter

                    transHisRecycle.visibility = View.VISIBLE
                    txtLoading.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}