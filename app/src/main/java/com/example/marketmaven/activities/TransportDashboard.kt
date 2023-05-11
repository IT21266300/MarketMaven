package com.example.marketmaven.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.marketmaven.R

class TransportDashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transportdashboard)


        val calTransport = findViewById<Button>(R.id.cal_trans_btn)
        calTransport.setOnClickListener{
            val intent1 = Intent(this, ItemList::class.java)
            startActivity(intent1)
        }


        val btnTranHis = findViewById<Button>(R.id.trans_his_btn)
        btnTranHis.setOnClickListener{
            val intent2 = Intent(this, TransportHistory::class.java)
            startActivity(intent2)
        }

        val btnTranData = findViewById<Button>(R.id.trans_price)
        btnTranData.setOnClickListener{
            val intent3 = Intent(this, TransportData::class.java)
            startActivity(intent3)
        }

    }
}