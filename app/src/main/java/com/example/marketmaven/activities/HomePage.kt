package com.example.marketmaven.activities
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.marketmaven.R

class HomePage : AppCompatActivity() {

    private  lateinit var CalFarmer: Button
    private lateinit var transList: Button
    private lateinit var sellerList: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)



        CalFarmer = findViewById(R.id.btnCalFarmer)
        transList = findViewById(R.id.transListBtn)
        sellerList = findViewById(R.id.sellerListBtn)



        CalFarmer.setOnClickListener {
            val intent1 = Intent(this, FarmerCalSetValues::class.java)
            startActivity(intent1)
        }

        transList.setOnClickListener {
            val intent2 = Intent(this, FarmerCalHistory::class.java)
            startActivity(intent2)
        }
        sellerList.setOnClickListener {
            val intent3 = Intent(this, ItermPriceEdit::class.java)
            startActivity(intent3)
        }


    }
}