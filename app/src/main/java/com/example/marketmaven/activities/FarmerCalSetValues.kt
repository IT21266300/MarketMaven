package com.example.marketmaven.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.marketmaven.R
import com.example.marketmaven.model.FarmerCalModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.DateFormat
import java.util.Calendar


class FarmerCalSetValues : AppCompatActivity() {
    lateinit var btnFarmUpdate: Button
    lateinit var resetBtn: Button
    lateinit var itermNameFarmer: EditText
    lateinit var itemWeight: EditText
    lateinit var edtTotalExpens: EditText

    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.farmer_cal2)

// Get references to UI elements
        itermNameFarmer = findViewById(R.id.itermNameFarmerEdit)
        itemWeight = findViewById(R.id.edt_weight)
        edtTotalExpens = findViewById(R.id.edt_total_expens)
        resetBtn = findViewById(R.id.resetBtn)
        btnFarmUpdate = findViewById(R.id.btnFarmUpdate)

        // Set farmer item name as text for itermNameFarmer EditText and disable editing
        itermNameFarmer.setText(intent.getStringExtra("iname").toString())

        itermNameFarmer.setEnabled(false)

        // Get reference to Firebase database
        dbRef = FirebaseDatabase.getInstance().getReference("Farmer")

        // Set click listener for "Update" button
        btnFarmUpdate.setOnClickListener {
            saveFarmerCalculationData()
        }
        // Set click listener for "Cancel" button
        resetBtn.setOnClickListener{
            val intentCancel = Intent(this, FarmerDash::class.java)
            startActivity(intentCancel)
        }
    }

    // Function to save farmer calculation data to Firebase database
    fun saveFarmerCalculationData(){
        val itemPrices = mapOf(
            "Avocado" to 150.00,
            "Banana" to 90.00,
            "Beans" to 200.00,
            "Cabbage" to 75.00
            // add more items and their prices as needed
        )

        // Get values of UI elements
        val farmerItem = itermNameFarmer.text.toString()
        val farmerItemPrice = itemPrices[farmerItem]
        val farmerItemWeight = itemWeight.text.toString()
        val edtTotalExpenseText = edtTotalExpens.text.toString()

// Check for empty weight field
        if(farmerItemWeight.isEmpty()){
            itemWeight.error = "Please Enter Weight"
            itemWeight.requestFocus()
            return
        }
// Check for valid item name
        if(farmerItemPrice == null){
            itermNameFarmer.error = "Please Enter a Valid Item Name"
            itermNameFarmer.requestFocus()
            return
        }
// Get current date
        val calender = Calendar.getInstance().time
        val calDate = DateFormat.getDateInstance().format(calender)
// Calculate farmer profit and total profit
        val farmerProfit = "%.2f".format(farmerItemPrice * farmerItemWeight.toDouble() )
        val farmerTotalProfit = "%.2f".format(farmerProfit.toDouble() - edtTotalExpenseText.toDouble())

// Generate unique ID for farmer calculation entry
        val farmerId = dbRef.push().key!!
        // Create FarmerCalModel object with data
        val farmer = FarmerCalModel(farmerId, farmerItem, farmerItemWeight, calDate,
            "%.2f".format(edtTotalExpenseText.toDouble()),
            farmerTotalProfit.toDouble())
// Save farmer calculation data to Firebase database
        dbRef.child(farmerId).setValue(farmer).addOnCompleteListener{
            Toast.makeText(this, "New Farmer Calculation Add Successfully", Toast.LENGTH_LONG).show()
            val intentDone = Intent(this, FarmerCalHistory::class.java)
            startActivity(intentDone)

        }.addOnFailureListener{ err ->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
        }

    }
}