package com.example.marketmaven

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class TransportHisItem : AppCompatActivity() {

    private lateinit var  itemName: TextView
    private lateinit var  itemWeight: TextView
    private lateinit var  itemPickup: TextView
    private lateinit var  itemDelivery: TextView
    private lateinit var itemDistance: TextView
    private lateinit var itemFuelEfficient: TextView
    private lateinit var itemTotalFuelEfficient: TextView
    private lateinit var itemFuelPrice: TextView
    private lateinit var itemTotalFuelCost: TextView
    private lateinit var itemDriverWage: TextView
    private lateinit var itemTotalCost: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transporthisitem)

        initView()
        setValuesToViews()
    }

    private fun initView(){
        itemName = findViewById(R.id.itemsName)
        itemWeight = findViewById(R.id.itemWeight)
        itemPickup = findViewById(R.id.itemPickUp)
        itemDelivery = findViewById(R.id.itemDelivery)
        itemDistance = findViewById(R.id.itemDistance)
        itemFuelEfficient = findViewById(R.id.itemFuelEfficient)
        itemTotalFuelEfficient = findViewById(R.id.itemTotalFuelEfficient)
        itemFuelPrice = findViewById(R.id.itemFuelPrice)
        itemTotalCost = findViewById(R.id.itemTotalFuelCost)
        itemDriverWage = findViewById(R.id.itemDriverWag)
        itemTotalCost = findViewById(R.id.transTotalCost)
        btnUpdate = findViewById(R.id.updateBtn)
        btnDelete = findViewById(R.id.deleteBtn)
    }

    private fun setValuesToViews(){
        itemName.text = intent.getStringExtra("transItem")
        itemWeight.text = intent.getStringExtra("transItemWeight")
        itemPickup.text = intent.getStringExtra("transPickUp")
        itemDelivery.text = intent.getStringExtra("transDelivery")
        itemDistance.text = intent.getStringExtra("transDistance")
        itemFuelEfficient.text = intent.getStringExtra("transFuelEfficient")
        itemTotalFuelEfficient.text = intent.getStringExtra("transTotalFuelEfficient")

        val doubleFuelPrice = intent.getStringExtra("transFuelPrice")?.toDouble() ?: 0.0
        val doubleTotalFuelCost = intent.getStringExtra("transTotalFuelCost")?.toDouble() ?: 0.0
        val doubleDriverWage = intent.getStringExtra("transDriverWage")?.toDouble() ?: 0.0
        val doubleTotalCost = intent.getStringExtra("transTotalCost")?.toDouble() ?: 0.0
        itemFuelPrice.text = doubleFuelPrice.toString()
        itemTotalFuelCost.text = doubleTotalFuelCost.toString()
        itemDriverWage.text = doubleDriverWage.toString()

        itemTotalCost.text = doubleTotalCost.toString()

    }
}