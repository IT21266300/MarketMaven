package com.example.marketmaven.validators

object FarmerCalculateValidator {

    fun validateInput(

                       farmerItem: String,
                       farmerItemWeight: String,
                       edtTotalExpens: String,
                       farmerTotalProfit: Double

    ): Boolean {
        return !(
                farmerItem.isEmpty() ||
                        farmerItemWeight.toDouble() <= 0 ||
                        edtTotalExpens.toDouble() <= 0 ||
                        farmerTotalProfit.toDouble() <= 0)
    }
}