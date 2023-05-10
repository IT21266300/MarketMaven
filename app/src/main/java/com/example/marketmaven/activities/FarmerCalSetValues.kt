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
    private lateinit var btnFarmCalculate: Button
    private lateinit var resetBtn: Button
    private lateinit var itermNameFarmer: EditText
    private lateinit var itemWeight: EditText
    private lateinit var edtTotalExpens: EditText


    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.farmer_cal2)

        itermNameFarmer = findViewById(R.id.itermNameFarmerEdit)
        itemWeight = findViewById(R.id.edt_weight)
        edtTotalExpens = findViewById(R.id.edt_total_expens)

        resetBtn = findViewById(R.id.resetBtn)
        btnFarmCalculate = findViewById(R.id.btnFarmUpdate)

        itermNameFarmer.setText(intent.getStringExtra("iname").toString())

        itermNameFarmer.setEnabled(false)

        dbRef = FirebaseDatabase.getInstance().getReference("Farmer")

        btnFarmCalculate = findViewById(R.id.btnFarmUpdate)

        btnFarmCalculate.setOnClickListener {
            saveFarmerCalculationData()
            val intent1 = Intent(this, FarmerCalResult::class.java)
            startActivity(intent1)
        }
        resetBtn.setOnClickListener{
            val intentCancel = Intent(this, FarmerDash::class.java)
            startActivity(intentCancel)
        }
    }


    private fun saveFarmerCalculationData(){
        val itemPrices = mapOf(
            "Avocado" to 30.00,
            "Banana" to 150.00,
            "Beans" to 250.00,
            "Cabbage" to 75.00
            // add more items and their prices as needed
        )
        val farmerItem = itermNameFarmer.text.toString()
        val farmerItemPrice = itemPrices[farmerItem]
        val farmerItemWeight = itemWeight.text.toString()
        val edtTotalExpenseText = edtTotalExpens.text.toString()


//        if(farmerItem.isEmpty()){
//            itermNameFarmer.error = "Please Enter Iterm Name"
//            itermNameFarmer.requestFocus()
//        }

        if(farmerItemWeight.isEmpty()){
            itemWeight.error = "Please Enter Value"

            itemWeight.requestFocus()
        }

        if (farmerItemPrice == null) {
            itermNameFarmer.error = "Please Enter a Valid Item Name"
            itermNameFarmer.requestFocus()
            return
        }


        val vegiPrice = 150.00


        val calender = Calendar.getInstance().time
        val calDate = DateFormat.getDateInstance().format(calender)

        val farmerProfit = "%.2f".format(farmerItemPrice * farmerItemWeight.toDouble() )
        val farmerTotalProfit = "%.2f".format(farmerProfit.toDouble() - edtTotalExpenseText.toDouble())




        val farmerId = dbRef.push().key!!
        val farmer = FarmerCalModel(farmerId, farmerItem, farmerItemWeight, calDate,
            "%.2f".format(edtTotalExpenseText.toDouble()),
            farmerTotalProfit.toDouble())

        dbRef.child(farmerId).setValue(farmer).addOnCompleteListener{
            Toast.makeText(this, "New Farmer Calculation Add Successfully", Toast.LENGTH_LONG).show()
            val intentDone = Intent(this, FarmerCalResult::class.java)
            startActivity(intentDone)

        }.addOnFailureListener{ err ->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
        }

    }
}