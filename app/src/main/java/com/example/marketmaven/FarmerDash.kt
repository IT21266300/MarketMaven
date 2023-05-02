package com.example.marketmaven
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FarmerDash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farmer_dash)

        val calTransport = findViewById<Button>(R.id.btnCalFarmer)
        calTransport.setOnClickListener{
            val intent1 = Intent(this, FarmerCalGetValues::class.java)
            startActivity(intent1)
        }


        val btnTranHis = findViewById<Button>(R.id.farmer_his_btn)
        btnTranHis.setOnClickListener{
            val intent2 = Intent(this, FarmerCalHistory::class.java)
            startActivity(intent2)
        }

        val btnTranData = findViewById<Button>(R.id.farmer_price)
        btnTranData.setOnClickListener{
            val intent3 = Intent(this, ItermPriceEdit::class.java)
            startActivity(intent3)
        }
    }
}