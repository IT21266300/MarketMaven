package com.example.marketmaven.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.marketmaven.R


class MainActivity : AppCompatActivity() {

    private  lateinit var btnCalFarmer: Button
    private lateinit var farmer_his_btn: Button
    private lateinit var farmer_price: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farmer_dash)

        btnCalFarmer = findViewById(R.id.btnCalFarmer)
        farmer_his_btn = findViewById(R.id.farmer_his_btn)
        farmer_price = findViewById(R.id.farmer_price)


        btnCalFarmer.setOnClickListener {
            val intent1 = Intent(this, FarmerCalSetValues::class.java)
            startActivity(intent1)
        }

        farmer_his_btn.setOnClickListener {
            val intent2 = Intent(this, FarmerCalHistory::class.java)
            startActivity(intent2)
        }
        farmer_price.setOnClickListener {
            val intent1 = Intent(this, ItermPriceEdit::class.java)
            startActivity(intent1)
        }


        // val firebase: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    }
}