package com.example.mad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        val btnTransport = findViewById<Button>(R.id.transportBtn)
        btnTransport.setOnClickListener{
            val intent1 = Intent(this, TransportDashboard::class.java)
            startActivity(intent1)
        }
    }
}