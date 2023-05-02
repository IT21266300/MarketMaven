package com.example.mad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TransportDashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transportdashboard)
        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        val calTransport = findViewById<Button>(R.id.cal_trans_btn)
        calTransport.setOnClickListener{
            val intent1 = Intent(this, CalculateTransport::class.java)
            startActivity(intent1)
        }

        val transportHistory = findViewById<Button>(R.id.trans_his_btn)
        transportHistory.setOnClickListener{
            val intent2 = Intent(this, Historypage::class.java)
            startActivity(intent2)
        }

        val transportDataEdit = findViewById<Button>(R.id.trans_price)
        transportDataEdit.setOnClickListener{
            val intent3 = Intent(this, TransDataEdit::class.java)
            startActivity(intent3)
        }

    }
}