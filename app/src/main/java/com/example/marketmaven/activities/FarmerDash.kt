package com.example.marketmaven.activities
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.marketmaven.R

class FarmerDash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farmer_dash)

        val btnCalFarmer = findViewById<Button>(R.id.btnCalFarmer)
        btnCalFarmer.setOnClickListener{
            val intent1 = Intent(this, FarmerCalSetValues::class.java)
            startActivity(intent1)
        }


        val farmerHisBtn = findViewById<Button>(R.id.farmer_his_btn)
        farmerHisBtn.setOnClickListener{
            val intent1 = Intent(this, FarmerCalHistory::class.java)
            startActivity(intent1)
        }

        val farmerPriceBtn = findViewById<Button>(R.id.farmer_price)
        farmerPriceBtn.setOnClickListener{
            val intent1 = Intent(this, ItermPriceEdit::class.java)
            startActivity(intent1)
        }
    }
}