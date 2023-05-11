package com.example.marketmaven.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.security.identity.AccessControlProfileId
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.madproject1.R
import com.example.madproject1.models01.ItemModel
import com.google.firebase.database.FirebaseDatabase

class ShowItemData : AppCompatActivity() {

    private lateinit var tvItemId: TextView
    private lateinit var tvItemName: TextView
    private lateinit var tvItemWeight: TextView
    private lateinit var tvItemDistance: TextView
    private lateinit var tvItemBuy: TextView
    private lateinit var tvItemSell: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_item_data)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("itemId").toString(),
                intent.getStringExtra("itemName").toString()
            )
        }
        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("itemId").toString()
            )
        }
    }

    private fun initView(){
        tvItemId = findViewById(R.id.tvItemId)
        tvItemName= findViewById(R.id.tvItemName)
        tvItemWeight = findViewById(R.id.tvItemWeight)
        tvItemDistance= findViewById(R.id.tvItemDistance)
        tvItemBuy = findViewById(R.id.tvItemBuy)
        tvItemSell= findViewById(R.id.tvItemSell)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete= findViewById(R.id.btnDelete)
    }
    private fun setValuesToViews(){
        tvItemId.text = intent.getStringExtra("itemId")
        tvItemName.text = intent.getStringExtra("itemName")
        tvItemWeight.text = intent.getStringExtra("itemWeight")
        tvItemDistance.text = intent.getStringExtra("itemDistance")
        tvItemBuy.text = intent.getStringExtra("buyingPrice")
        tvItemSell.text = intent.getStringExtra("sellingPrice")
    }

    private fun deleteRecord(
        id:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Items").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Data Deleted!",Toast.LENGTH_LONG).show()
            val intent = Intent(this, DisplayActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { err ->
            Toast.makeText(this, "Deleting has error of ${err.message}",Toast.LENGTH_LONG).show()
        }
    }

    private fun openUpdateDialog(
        itmId:String,
        itmName: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_item,null)

        mDialog.setView(mDialogView)

        val edtName     = mDialogView.findViewById<EditText>(R.id.edtName)
        val edtWeight   = mDialogView.findViewById<EditText>(R.id.edtWeight)
        val edtDistance = mDialogView.findViewById<EditText>(R.id.edtDistance)
        val edtBuy      = mDialogView.findViewById<EditText>(R.id.edtBuy)
        val edtSell     = mDialogView.findViewById<EditText>(R.id.edtSell)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        edtName.setText(intent.getStringExtra("itemName").toString())
        edtWeight.setText(intent.getStringExtra("itemWeight").toString())
        edtDistance.setText(intent.getStringExtra("itemDistance").toString())
        edtBuy.setText(intent.getStringExtra("buyingPrice").toString())
        edtSell.setText(intent.getStringExtra("sellingPrice").toString())

        mDialog.setTitle("Update $itmName Data!")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateItemData(
                itmId,
                edtName.text.toString(),
                edtWeight.text.toString(),
                edtDistance.text.toString(),
                edtBuy.text.toString(),
                edtSell.text.toString()
            )
            Toast.makeText(applicationContext, "Item Data Updated", Toast.LENGTH_LONG).show()

            //setting new updated values to text views
            tvItemName.text = edtName.text.toString()
            tvItemWeight.text = edtWeight.text.toString()
            tvItemDistance.text = edtDistance.text.toString()
            tvItemBuy.text = edtBuy.text.toString()
            tvItemSell.text = edtSell.text.toString()

            alertDialog.dismiss()
        }

    }
    private fun updateItemData(
        id:String,
        name:String,
        weight:String,
        distance:String,
        buy:String,
        sell:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Items").child(id)
        val itemInfo = ItemModel(id,name,weight,distance,buy,sell)
        dbRef.setValue(itemInfo)
    }
}