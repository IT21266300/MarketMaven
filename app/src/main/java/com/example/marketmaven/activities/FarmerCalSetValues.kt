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


class FarmerCalSetValues : AppCompatActivity() {
    private lateinit var btnFarmCalculate: Button
    private lateinit var resetBtn: Button
    private lateinit var itermNameFarmer: EditText
    private lateinit var itemWeight: EditText
    private lateinit var edtTotalExpens: EditText


    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.farmer_cal2)

        itermNameFarmer = findViewById(R.id.itermNameFarmer)
        itemWeight = findViewById(R.id.edt_weight)
        edtTotalExpens = findViewById(R.id.edt_total_expens)

        resetBtn = findViewById(R.id.resetBtn)
        btnFarmCalculate = findViewById(R.id.btnFarmCalculate)

        dbRef = FirebaseDatabase.getInstance().getReference("Farmer")

        btnFarmCalculate = findViewById(R.id.btnFarmCalculate)

        btnFarmCalculate.setOnClickListener {
            saveFarmerCalculationData()

        }
        resetBtn.setOnClickListener{
            val intentCancel = Intent(this, FarmerDash::class.java)
            startActivity(intentCancel)
        }
    }

    private fun saveFarmerCalculationData(){
        val farmerItem = itermNameFarmer.text.toString()
        val farmerItemWeight = itemWeight.text.toString()
        val edtTotalExpens = edtTotalExpens.text.toString()


        if(farmerItem.isEmpty()){
            itermNameFarmer.error = "Please Enter Iterm Name"
        }

        val vegiPrice = 150.00



        val farmerProfit = (vegiPrice * farmerItemWeight.toDouble() )
        val farmerTotalProfit = farmerProfit - edtTotalExpens.toDouble()



        val farmerId = dbRef.push().key!!
        val farmer = FarmerCalModel(farmerId, farmerItem, farmerItemWeight, edtTotalExpens, farmerTotalProfit)
        dbRef.child(farmerId).setValue(farmer).addOnCompleteListener{
            Toast.makeText(this, "New Farmer Calculation Add Successfully", Toast.LENGTH_LONG).show()
            val intentDone = Intent(this, FarmerCalResult::class.java)
            startActivity(intentDone)

        }.addOnFailureListener{ err ->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
        }

    }
}