package com.example.marketmaven.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.marketmaven.R


class MainActivity : AppCompatActivity() {

    private  lateinit var btnCalFarmer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farmer_dash)

        btnCalFarmer = findViewById(R.id.btnCalFarmer)


        btnCalFarmer.setOnClickListener {
            val intent1 = Intent(this, FarmerCalSetValues::class.java)
            startActivity(intent1)
        }


        // val firebase: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    }
}