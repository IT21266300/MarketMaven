package com.example.marketmaven

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.marketmaven.models.TransportModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CalculateTransport : AppCompatActivity() {

    private lateinit var itemName: EditText
    private lateinit var itemWeight: EditText
    private lateinit var pickupLocation: EditText
    private lateinit var deliveryLocation: EditText
    private lateinit var distance: EditText
    private lateinit var fuelEfficient: EditText
    private lateinit var calculateBtn: Button
    private lateinit var cancelBtn: Button

    private lateinit var dbRef: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transcalculate)

        itemName = findViewById(R.id.itemName)
        itemWeight = findViewById(R.id.edt_weight)
        pickupLocation = findViewById(R.id.edt_pickup)
        deliveryLocation = findViewById(R.id.edt_delivery)
        distance = findViewById(R.id.edt_distance)
        fuelEfficient = findViewById(R.id.edt_fuelEfficient)
        cancelBtn = findViewById(R.id.cancelBtn)
        calculateBtn = findViewById(R.id.submitBtn)

        dbRef = FirebaseDatabase.getInstance().getReference("Transport")

        calculateBtn.setOnClickListener {
            saveTransportData()
        }

        cancelBtn.setOnClickListener {
            val intentCancel = Intent(this, TransportDashboard::class.java)
            startActivity(intentCancel)
        }

    }

    private fun saveTransportData(){
        val transItem = itemName.text.toString()
        val transItemWeight = itemWeight.text.toString()
        val transPickUp = pickupLocation.text.toString()
        val transDelivery = deliveryLocation.text.toString()
        val transDistance = distance.text.toString()
        val transFuelEfficient = fuelEfficient.text.toString()

        if(transItem.isEmpty()){
            itemName.error = "Please Enter Value"
        }

        if(transItemWeight.isEmpty()){
            itemWeight.error = "Please Enter Value"
        }

        if(transPickUp.isEmpty()){
            pickupLocation.error = "Please Enter Value"
        }

        if(transDelivery.isEmpty()){
            deliveryLocation.error = "Please Enter Value"
        }

        if(transDistance.isEmpty()){
            distance.error = "Please Enter Value"
        }

        if(transFuelEfficient.isEmpty()){
            fuelEfficient.error = "Please Enter Value"
        }

        val transFuelPrice = 320.30
        val transDriverWage = 1000.00
        val transTotalFuelEfficient = transDistance.toDouble() / transFuelEfficient.toDouble()
        val transTotalFuelCost = transFuelPrice * transTotalFuelEfficient

        val transTotalCost = (transTotalFuelCost + transDriverWage) * transItemWeight.toDouble()

        val transId = dbRef.push().key!!
        val transport = TransportModel(transId, transItem, transItemWeight, transPickUp, transDelivery, transDistance,
            transFuelEfficient, transFuelPrice, transTotalFuelEfficient, transTotalFuelCost, transDriverWage, transTotalCost)
        dbRef.child(transId).setValue(transport).addOnCompleteListener{
            Toast.makeText(this, "New Transport Add Successfully", Toast.LENGTH_LONG).show()
            val intentDone = Intent(this, TransportHistory::class.java)
            startActivity(intentDone)

        }.addOnFailureListener{ err ->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
        }

    }

}