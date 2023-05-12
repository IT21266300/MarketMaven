package com.example.marketmaven.activities
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.marketmaven.R

// This class represents the main activity of the Farmer Dashboard
class FarmerDash : AppCompatActivity() {
    // This function is called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the activity layout using the farmer_dash XML file
        setContentView(R.layout.activity_farmer_dash)

        val btnCalFarmer = findViewById<Button>(R.id.btnCalFarmer)
        // Get the "Calculate Farmer" button from the layout
        btnCalFarmer.setOnClickListener{
            val intent1 = Intent(this, ItemListFarmer::class.java)
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