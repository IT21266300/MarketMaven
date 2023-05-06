package com.example.marketmaven.activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.marketmaven.R

import com.google.firebase.database.FirebaseDatabase

class FarmerCalResult : AppCompatActivity() {

    private lateinit var  itemName: TextView
    private lateinit var  itemWeight: TextView
    private lateinit var  farmExpense: TextView
    private lateinit var  subProfit: TextView

    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_farmer_calculation)
        initView()
        setValuesToViews()

//        btnUpdate.setOnClickListener {
//            openUpdateDialog(
//                intent.getStringExtra("farmerId").toString(),
//                intent.getStringExtra("farmerItem").toString()
//            )
//        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("farmerId").toString()
            )
        }
    }

    private fun initView(){
        itemName = findViewById(R.id.itemName)
        itemWeight = findViewById(R.id.itemWeight)
        farmExpense = findViewById(R.id.farmExpense)
        subProfit = findViewById(R.id.subProfit)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews(){
        itemName.text = intent.getStringExtra("farmerItem")
        itemWeight.text = intent.getStringExtra("farmerItemWeight")
//        farmExpense.text = intent.getStringExtra("farmerExpense")
//        subProfit.text = intent.getStringExtra("farmerProfit")

        val doubleFarmExpense = intent.getStringExtra("farmerExpense")?.toDouble() ?: 0.0
        val doubleSubProfit = intent.getStringExtra("farmerSubProfit")?.toDouble() ?: 0.0

        subProfit.text = doubleSubProfit.toString()
        farmExpense.text = doubleFarmExpense.toString()

    }

    private fun deleteRecord(
        farmerId:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Farmer").child(farmerId)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Data Deleted!",Toast.LENGTH_LONG).show()
            val intent = Intent(this, FarmerDash::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { err ->
            Toast.makeText(this, "Deleting has error of ${err.message}",Toast.LENGTH_LONG).show()
        }
    }

//    private fun openUpdateDialog(
//        farmerId:String,
//        farmerItem: String
//    ){
//        val mDialog = AlertDialog.Builder(this)
//        val inflater = layoutInflater
//        val mDialogView = inflater.inflate(R.layout.farmer_caledit,null)
//
//        mDialog.setView(mDialogView)
//
//        val itermNameFarmerEdit     = mDialogView.findViewById<EditText>(R.id.itermNameFarmerEdit)
//        val edtWeight   = mDialogView.findViewById<EditText>(R.id.edt_weight)
//        val edtTotalExpens = mDialogView.findViewById<EditText>(R.id.edt_total_expens)
//
//
//        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnFarmUpdate)
//
//        itermNameFarmerEdit.setText(intent.getStringExtra("farmerItem").toString())
//        edtWeight.setText(intent.getStringExtra("farmerItemWeight").toString())
//        edtTotalExpens.setText(intent.getStringExtra("edtTotalExpens").toString())
//
//
//        mDialog.setTitle("Update $farmerItem Data!")
//
//        val alertDialog = mDialog.create()
//        alertDialog.show()
//
//        btnUpdateData.setOnClickListener {
//            updateItemData(
//                farmerId,
//                itermNameFarmerEdit.text.toString(),
//                edtWeight.text.toString(),
//                edtTotalExpens.text.toString(),
//
//            )
//            Toast.makeText(applicationContext, "Item Data Updated", Toast.LENGTH_LONG).show()
//
//            //setting new updated values to text views
//            tvItemName.text = edtName.text.toString()
//            tvItemWeight.text = edtWeight.text.toString()
//            tvItemDistance.text = edtDistance.text.toString()
//            tvItemBuy.text = edtBuy.text.toString()
//            tvItemSell.text = edtSell.text.toString()
//
//            alertDialog.dismiss()
//        }
//
//    }
//
//    private fun updateItemData(id: String, name: String, weight: String, distance: String) {
//
//    }
//
//    private fun updateItemData(
//        id:String,
//        name:String,
//        weight:String,
//        distance:String,
//        buy:String,
//        sell:String
//    ){
//        val dbRef = FirebaseDatabase.getInstance().getReference("Items").child(id)
//        val itemInfo = ItemModel(id,name,weight,distance,buy,sell)
//        dbRef.setValue(itemInfo)
//    }

}