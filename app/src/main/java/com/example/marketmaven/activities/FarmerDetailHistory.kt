package com.example.marketmaven.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marketmaven.R
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.marketmaven.model.FarmerCalModel
import com.google.firebase.database.FirebaseDatabase
import java.text.DateFormat
import java.util.Calendar

class FarmerDetailHistory : AppCompatActivity() {

    private lateinit var  finame: TextView
    private lateinit var  fiweight: TextView
    private lateinit var  fidate: TextView
    private lateinit var  fiexpenses: TextView

    private lateinit var  fitotalprofit: TextView
    private lateinit var  btnUpdate: ImageButton
    private lateinit var  btnDelete: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farmer_detail_history)

        initView()
        setValuesToViews()

        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("farmerId").toString()
            )
        }

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("farmerId").toString(),
                intent.getStringExtra("farmerItem").toString()
            )
        }

    }

    private fun deleteRecord(
        farmerId: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Farmer").child(farmerId)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Delete Farmer Calculation Data", Toast.LENGTH_LONG).show()
            val intent = Intent(this, FarmerCalHistory::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Delete Error ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView(){
        finame = findViewById(R.id.famItemName)
        fiweight = findViewById(R.id.fweight)
        fidate = findViewById(R.id.fdate)
        fiexpenses = findViewById(R.id.fexpenses)

        fitotalprofit = findViewById(R.id.ftotalprofit)
        btnDelete = findViewById(R.id.del_btn)
        btnUpdate= findViewById(R.id.update_btn)
    }

    private fun setValuesToViews(){
        finame.text = intent.getStringExtra("farmerItem")
        fiweight.text = intent.getStringExtra("farmerItemWeight")
        fidate.text = intent.getStringExtra("calDate")
        fiexpenses.text = intent.getStringExtra("edtTotalExpens")
        fitotalprofit.text = intent.getStringExtra("farmerTotalProfit")

    }

    @SuppressLint("MissingInflatedId")
    private fun openUpdateDialog(
        farmerId: String,
        farmerItem: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.farmer_update, null)

        mDialog.setView(mDialogView)

        val edtname = mDialogView.findViewById<EditText>(R.id.edtname)
        val edtweight = mDialogView.findViewById<EditText>(R.id.edtweight)
        val edtexpenses = mDialogView.findViewById<EditText>(R.id.edtexpenses)
        val transUpdateBtn = mDialogView.findViewById<Button>(R.id.farmerUpdateBtn)

        edtname.setText(intent.getStringExtra("farmerItem").toString())
        edtweight.setText(intent.getStringExtra("farmerItemWeight").toString())
        edtexpenses.setText(intent.getStringExtra("edtTotalExpens").toString())



        val alertDialog = mDialog.create()
        alertDialog.show()

        transUpdateBtn.setOnClickListener {
            updateFarmerData(
                farmerId,
                edtname.text.toString(),
                edtweight.text.toString(),
                edtexpenses.text.toString(),
            )

            Toast.makeText(applicationContext, "Farmer Calculation Data Updated", Toast.LENGTH_LONG).show()


            finame.text = edtname.text.toString()
            fiweight.text = edtweight.text.toString()
            fiexpenses.text = edtexpenses.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateFarmerData(
        farmerId: String,
        edtname: String,
        edtweight: String,
        edtexpenses: String,

    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Farmer").child(farmerId)
        val itemPrices = mapOf(
            "Avocado" to 30.00,
            "Banana" to 150.00,
            "Beans" to 250.00,
            "Cabbage" to 75.00
            // add more items and their prices as needed
        )

        val farmerItemPrice = itemPrices[edtname]

        if(edtweight.isEmpty()){
            fiweight.error = "Please Enter Weight"
            fiweight.requestFocus()
        }
        if(farmerItemPrice == null){
            finame.error = "Please Enter a Valid Item Name"
            finame.requestFocus()
            return
        }

        val calender = Calendar.getInstance().time
        val farmerCalDate = DateFormat.getDateInstance().format(calender)

        val farmerProfit = "%.2f".format(farmerItemPrice * edtweight.toDouble() )
        val farmerTotalProfit = "%.2f".format(farmerProfit.toDouble() - edtexpenses.toDouble())

        fitotalprofit.text =  farmerTotalProfit



        val farmerInfo = FarmerCalModel(farmerId, edtname, farmerCalDate,
            "%.2f".format(edtweight.toDouble()),
            farmerTotalProfit)

        dbRef.setValue(farmerInfo)
    }

}
