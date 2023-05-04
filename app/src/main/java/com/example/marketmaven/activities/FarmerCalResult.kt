package com.example.marketmaven.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.marketmaven.R

class FarmerCalResult : AppCompatActivity() {

    private lateinit var  itemName: TextView
    private lateinit var  itemWeight: TextView
    private lateinit var  farmExpense: TextView
    private lateinit var  subProfit: TextView

    private lateinit var update_btn: Button
    private lateinit var btnDelete: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_farmer_calculation)
        initView()
        setValuesToViews()
    }

    private fun initView(){
        itemName = findViewById(R.id.itemName)
        itemWeight = findViewById(R.id.itemWeight)
        farmExpense = findViewById(R.id.farmExpense)
        subProfit = findViewById(R.id.subProfit)

        update_btn = findViewById(R.id.update_btn)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews(){
        itemName.text = intent.getStringExtra("farmerItem")
        itemWeight.text = intent.getStringExtra("farmerItemWeight")
        farmExpense.text = intent.getStringExtra("farmerExpense")
//        subProfit.text = intent.getStringExtra("farmerProfit")


        val doubleSubProfit = intent.getStringExtra("farmerSubProfit")?.toDouble() ?: 0.0

        subProfit.text = doubleSubProfit.toString()

    }
}