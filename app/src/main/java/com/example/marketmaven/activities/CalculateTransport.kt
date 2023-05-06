package com.example.marketmaven.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.marketmaven.R
import com.example.marketmaven.models.TransportModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CalculateTransport : AppCompatActivity() {

    lateinit var itemName: EditText
    lateinit var itemWeight: EditText
    lateinit var itemWeightFactor: EditText
    lateinit var pickupLocation: EditText
    lateinit var deliveryLocation: EditText
    lateinit var distance: EditText
    lateinit var fuelEfficient: EditText
    lateinit var fuelPrice: EditText
    lateinit var driverWage: EditText
    private lateinit var calculateBtn: Button
    private lateinit var cancelBtn: Button

    lateinit var dbRef: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transcalculate)

        itemName = findViewById(R.id.itemName)
        itemWeight = findViewById(R.id.edt_weight)
        itemWeightFactor = findViewById(R.id.edt_weight_factor)
        pickupLocation = findViewById(R.id.edt_pickup)
        deliveryLocation = findViewById(R.id.edt_delivery)
        distance = findViewById(R.id.edt_distance)
        fuelEfficient = findViewById(R.id.edt_fuelEfficient)
        fuelPrice = findViewById(R.id.edt_fuelPrice)
        driverWage = findViewById(R.id.edt_driverWage)
        cancelBtn = findViewById(R.id.cancelBtn)
        calculateBtn = findViewById(R.id.submitBtn)


        itemName.setText(intent.getStringExtra("iname").toString())

        itemName.setEnabled(false)

        dbRef = FirebaseDatabase.getInstance().getReference("Transport")

        calculateBtn.setOnClickListener {
            saveTransportData()
        }

        cancelBtn.setOnClickListener {
            val intentCancel = Intent(this, TransportDashboard::class.java)
            startActivity(intentCancel)
        }

    }

    fun saveTransportData(){
        val transItem = itemName.text.toString()
        val transItemWeight = itemWeight.text.toString()
        val transWeightFactor = itemWeightFactor.text.toString()
        val transPickUp = pickupLocation.text.toString()
        val transDelivery = deliveryLocation.text.toString()
        val transDistance = distance.text.toString()
        val transFuelEfficient = fuelEfficient.text.toString()
        val transFuelPrice = fuelPrice.text.toString()
        val transDriverWage = driverWage.text.toString()

        if(transItem.isEmpty()){
            itemName.error = "Please Enter Value"
            itemName.requestFocus()
        }

        if(transItemWeight.isEmpty()){
            itemWeight.error = "Please Enter Value"
            itemName.requestFocus()
        }

        if(transWeightFactor.isEmpty()){
            itemWeightFactor.error = "Please Enter Value"
            itemName.requestFocus()
        }

        if(transPickUp.isEmpty()){
            pickupLocation.error = "Please Enter Value"
            itemName.requestFocus()
        }

        if(transDelivery.isEmpty()){
            deliveryLocation.error = "Please Enter Value"
            itemName.requestFocus()
        }

        if(transDistance.isEmpty()){
            distance.error = "Please Enter Value"
            itemName.requestFocus()
        }

        if(transFuelEfficient.isEmpty()){
            fuelEfficient.error = "Please Enter Value"
            itemName.requestFocus()
        }

        if(transFuelPrice.isEmpty()){
            fuelPrice.error = "Please Enter Value"
            itemName.requestFocus()
        }

        if(transDriverWage.isEmpty()){
            driverWage.error = "Please Enter Value"
            itemName.requestFocus()
        }


        val transTotalFuelEfficient = "%.2f".format(1 / transFuelEfficient.toDouble())
        val transTotalFuelCost = "%.2f".format( (transDistance.toDouble() / transFuelEfficient.toDouble()) * transFuelPrice.toDouble())
        val transTotalWeightFactor = "%.2f".format( transWeightFactor.toDouble() * transItemWeight.toDouble())
        val transTotalCost = "%.2f".format((transTotalFuelCost.toDouble() + transDriverWage.toDouble() + transTotalWeightFactor.toDouble()))

        val transId = dbRef.push().key!!
        val transport = TransportModel(transId, transItem,
            "%.2f".format(transItemWeight.toDouble()),
            "%.2f".format(transWeightFactor.toDouble()),
            "%.2f".format(transTotalWeightFactor.toDouble()),
            transPickUp, transDelivery,
            "%.2f".format(transDistance.toDouble()),
            "%.2f".format(transFuelEfficient.toDouble()),
            "%.2f".format(transFuelPrice.toDouble()), transTotalFuelEfficient, transTotalFuelCost,
            "%.2f".format(transDriverWage.toDouble()),
            transTotalCost)
        dbRef.child(transId).setValue(transport).addOnCompleteListener{
            Toast.makeText(this, "New Transport Add Successfully", Toast.LENGTH_LONG).show()
            val intentDone = Intent(this, TransportHistory::class.java)
            startActivity(intentDone)

        }.addOnFailureListener{ err ->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
        }

    }

}