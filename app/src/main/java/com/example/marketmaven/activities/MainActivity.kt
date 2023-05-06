package com.example.marketmaven.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.marketmaven.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var btnTransport: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        btnTransport = findViewById(R.id.transportBtn)
        btnTransport.setOnClickListener{
            val intent1 = Intent(this, TransportDashboard::class.java)
            startActivity(intent1)
        }
    }
}