package com.example.marketmaven.activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.marketmaven.R

import com.google.firebase.database.FirebaseDatabase

class FarmerCalResult : AppCompatActivity() {

    private lateinit var  itemName: TextView
    private lateinit var  itemWeight: TextView
    private lateinit var  farmExpense: TextView
    private lateinit var  subProfit: TextView

    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_farmer_calculation)
        initView()
        setValuesToViews()


        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("farmerId").toString()
            )
        }
    }

    private fun initView(){
        itemName = findViewById(R.id.itemName)
        itemWeight = findViewById(R.id.itemWeight)
        farmExpense = findViewById(R.id.farmExpense)
        subProfit = findViewById(R.id.subProfit)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews(){
        itemName.text = intent.getStringExtra("farmerItem")
        itemWeight.text = intent.getStringExtra("farmerItemWeight")


        val doubleFarmExpense = intent.getStringExtra("farmerExpense")?.toDouble() ?: 0.0
        val doubleSubProfit = intent.getStringExtra("farmerSubProfit")?.toDouble() ?: 0.0

        subProfit.text = doubleSubProfit.toString()
        farmExpense.text = doubleFarmExpense.toString()

    }

    private fun deleteRecord(
        farmerId:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Farmer").child(farmerId)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Data Deleted!",Toast.LENGTH_LONG).show()
            val intent = Intent(this, FarmerDash::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { err ->
            Toast.makeText(this, "Deleting has error of ${err.message}",Toast.LENGTH_LONG).show()
        }
    }

}