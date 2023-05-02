package com.example.marketmaven

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    private lateinit var btnFarmCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.farmer_cal2)

        btnFarmCalculate = findViewById(R.id.btnFarmCalculate)

        btnFarmCalculate.setOnClickListener {
            val intent = Intent(this, FamCalInsert::class.java)
            startActivity(intent)
        }

        // val firebase: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    }
}