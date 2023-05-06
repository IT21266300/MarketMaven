package com.example.madproject1.activities01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.madproject1.models01.ItemModel
import com.example.madproject1.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.view.View

class ItemDataInsert : AppCompatActivity() {

    private lateinit var sellingName: EditText
    private lateinit var sellingWeight: EditText
    private lateinit var sellingDistance: EditText
    private lateinit var sellingBuyPrice: EditText
    private lateinit var sellingSellPrice: EditText
    private lateinit var insertButton: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_data_insert)

        sellingName=findViewById(R.id.selling_item_name1_edit)
        sellingWeight=findViewById(R.id.selling_item_weight1_edit)
        sellingDistance=findViewById(R.id.selling_item_distance1_edit)
        sellingBuyPrice=findViewById(R.id.selling_item_buyprice1_edit)
        sellingSellPrice=findViewById(R.id.selling_item_selprice1_edit)
        insertButton=findViewById(R.id.insert_button)

        dbRef = FirebaseDatabase.getInstance().getReference("Items")
        insertButton.setOnClickListener {
            saveItemData()
        }
    }
    private fun saveItemData(){
        //getting input values
        val itmName = sellingName.text.toString()
        val itmWeight = sellingWeight.text.toString()
        val transportDistance = sellingDistance.text.toString()
        val buyingPrice = sellingBuyPrice.text.toString()
        val sellingPrice = sellingSellPrice.text.toString()


        //validation Part
        if(itmName.isEmpty()){
            sellingName.error="Please Enter Item Name"
        }
        if(itmWeight.isEmpty()){
            sellingWeight.error="Please Enter Item Weight"
        }
        if(transportDistance.isEmpty()){
            sellingDistance.error="Please Enter Distance"
        }
        if(buyingPrice.isEmpty()){
            sellingBuyPrice.error="Please Enter buying Price"
        }
        if(sellingPrice.isEmpty()){
            sellingSellPrice.error="Please Enter Selling Price"
        }

        if(itmName.isEmpty()){
            sellingName.error="Please Enter Item Name"
        }
        if(itmWeight.isEmpty()){
            sellingWeight.error="Please Enter Item Weight"
        }
        if(transportDistance.isEmpty()){
            sellingDistance.error="Please Enter Distance"
        }
        if(buyingPrice.isEmpty()){
            sellingBuyPrice.error="Please Enter buying Price"
        }
        if(sellingPrice.isEmpty()){
            sellingSellPrice.error="Please Enter Selling Price"
        }

        //generete unique ID to avoid data redundancy/duplicate
        val itmId = dbRef.push().key!!

        val items = ItemModel(itmId,itmName,itmWeight,transportDistance, buyingPrice,sellingPrice)


        dbRef.child(itmId).setValue(items)
            .addOnCompleteListener {
                Toast.makeText(this,"Sucessfully Inserted",Toast.LENGTH_LONG).show()

                sellingName.text.clear()
                sellingWeight.text.clear()
                sellingDistance.text.clear()
                sellingBuyPrice.text.clear()
                sellingSellPrice.text.clear()

            }.addOnFailureListener {err ->
                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
            }

    }
}


