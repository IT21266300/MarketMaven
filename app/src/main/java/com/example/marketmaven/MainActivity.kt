package com.example.marketmaven

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    private  lateinit var btnCalFarmer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farmer_dash)

        btnCalFarmer = findViewById(R.id.btnCalFarmer)


        btnCalFarmer.setOnClickListener {
            val intent = Intent(this, FarmerCalGetValues::class.java)
            startActivity(intent)
        }


        // val firebase: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    }
}