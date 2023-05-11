package com.example.marketmaven.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.madproject1.R

class MainActivity : AppCompatActivity() {

    private lateinit var btnInsertData: Button
    private lateinit var btnFetchData: Button
    private lateinit var btnCalculateAmount: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_layout)

        btnInsertData = findViewById(R.id.buttonMain1)
        btnFetchData = findViewById(R.id.buttonMain2)
        btnCalculateAmount = findViewById(R.id.buttonMain3)

        btnInsertData.setOnClickListener {

            val intent = Intent(this, ItemDataInsert::class.java)
            startActivity(intent)
        }
        btnFetchData.setOnClickListener {
            val intent = Intent(this, DisplayActivity::class.java)
            startActivity(intent)
        }
        btnCalculateAmount.setOnClickListener {
            val intent = Intent(this, CalculateCost::class.java)
            startActivity(intent)
        }

//        setContentView(R.layout.activity_main)
//        setContentView(R.layout.activity_selling_item)
//        setContentView(R.layout.activity_itemcost_history)
//        setContentView(R.layout.activity_calculate_cost)
//        setContentView(R.layout.activity_item_data_insert)






    }
}