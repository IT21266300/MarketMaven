package com.example.marketmaven.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.marketmaven.R

class CalculateCost : AppCompatActivity() {

    lateinit var btnCalAdd: Button

    @SuppressLint("MissingInflatedId")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate_cost)

        btnCalAdd = findViewById(R.id.btnCalInsert)

        btnCalAdd.setOnClickListener {
            val intent = Intent(this, ItemDataInsert::class.java)
            startActivity(intent)
        }

        val calculateButton = findViewById<Button>(R.id.button_calculate)
        val weightEditText = findViewById<EditText>(R.id.sel_item_weight_edit)
        val distanceEditText = findViewById<EditText>(R.id.sel_item_distance_edit)
        val sellingPriceEditText = findViewById<EditText>(R.id.sel_item_distance_editt)
        val itemSpinner = findViewById<Spinner>(R.id.item_spinner)

        calculateButton.setOnClickListener {
            val weight = weightEditText.text.toString().toDoubleOrNull()
            val distance = distanceEditText.text.toString().toDoubleOrNull()
            val selectedItem = itemSpinner.selectedItem.toString()

            if (weight == null || distance == null) {
                Toast.makeText(this, "Please enter valid weight and distance", Toast.LENGTH_SHORT).show()
            } else {

                val markup = if (selectedItem == "Vegetable") 1.1 else 1.3
                val sellingPrice = weight * distance * markup
//                val sellingPrice = (weight * distance * 1.1).toString()
                sellingPriceEditText.setText(String.format("%.2f", sellingPrice))
            }
        }
    }
}