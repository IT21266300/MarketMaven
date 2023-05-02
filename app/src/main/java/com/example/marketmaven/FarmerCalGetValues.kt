package com.example.marketmaven

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FarmerCalGetValues : AppCompatActivity() {
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
            val intent = Intent(this, FamCalInsert::class.java)
            startActivity(intent)
        }
    }

    private fun saveFarmerCalculationData(){
        val farmerItem = itermNameFarmer.text.toString()
        val farmerItemWeight = itemWeight.text.toString()
        val edtTotalExpens = edtTotalExpens.text.toString()


        if(farmerItem.isEmpty()){
            itermNameFarmer.error = "Please Enter Iterm Name"
        }

        val farmerId = dbRef.push().key!!
        val farmer = FarmerCalModel(farmerId, farmerItem, farmerItemWeight, edtTotalExpens)
        dbRef.child(farmerId).setValue(farmer).addOnCompleteListener{
            Toast.makeText(this, "New Farmer Calculation Add Successfully", Toast.LENGTH_LONG).show()

        }.addOnFailureListener{ err ->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
        }

    }
}