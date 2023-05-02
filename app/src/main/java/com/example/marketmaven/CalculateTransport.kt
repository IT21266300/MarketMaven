package com.example.marketmaven

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CalculateTransport : AppCompatActivity() {

    private lateinit var itemName: EditText
    private lateinit var itemWeight: EditText
    private lateinit var pickupLocation: EditText
    private lateinit var deliveryLocation: EditText
    private lateinit var distance: EditText
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
        cancelBtn = findViewById(R.id.cancelBtn)
        calculateBtn = findViewById(R.id.submitBtn)

        dbRef = FirebaseDatabase.getInstance().getReference("Transport")

        calculateBtn.setOnClickListener {
            saveTransportData()
        }

    }

    private fun saveTransportData(){
        val transItem = itemName.text.toString()
        val transItemWeight = itemWeight.text.toString()
        val transPickUp = pickupLocation.text.toString()
        val transDelivery = deliveryLocation.text.toString()
        val transDistance = distance.text.toString()

        if(transItem.isEmpty()){
            itemName.error = "Please"
        }

        val transId = dbRef.push().key!!
        val transport = TransportModel(transId, transItem, transItemWeight, transPickUp, transDelivery, transDistance)
        dbRef.child(transId).setValue(transport).addOnCompleteListener{
            Toast.makeText(this, "New Transport Add Successfully", Toast.LENGTH_LONG).show()

        }.addOnFailureListener{ err ->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
        }

    }

}