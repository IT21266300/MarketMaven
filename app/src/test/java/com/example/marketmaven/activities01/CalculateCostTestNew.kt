package com.example.madproject1.activities01

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class CalculateCostTestNew {
    //****************VEGETABLE******************
    //Vegetable get 1.1 of profit
    @Test
    fun testCalculateSellingPriceVegetable() {
        // Arrange
        val weight = 10.0
        val buyingPrice = 5.0
        val expectedSellingPrice = 55.0 // (5.0*10.0 * 1.1%)
        val priceCalculator = CalculateCost()

        // Act
        val actualSellingPrice: Double = priceCalculator.calculateSellingPrice(weight, buyingPrice)

        // Assert
        assertEquals(expectedSellingPrice, actualSellingPrice, 0.001)
        //in here it accept floting points
    }

    private class CalculateCost {
        fun calculateSellingPrice(weight: Double, buyingPrice: Double): Double {
            // Placeholder implementation, replace with actual implementation
            val profitPercentage = 1.1 //profit will be 1.1%
            return buyingPrice * weight*profitPercentage
        }
    }


    //****************FRUITS******************
    //Vegetable get 1.3 of profit
    @Test
    fun testCalculateSellingPriceFruit() {
        // Arrange
        val weight = 10.0
        val buyingPrice = 5.0
        val expectedSellingPrice = 65.0 // (5.0*10.0 * 1.3%)
        val priceCalculator = CalculateCost2()

        // Act
        val actualSellingPrice: Double = priceCalculator.calculateSellingPrice2(weight, buyingPrice)

        // Assert
        assertEquals(expectedSellingPrice, actualSellingPrice, 0.001)
        //in here(0.001) it accept floting points
    }

    private class CalculateCost2 {
        fun calculateSellingPrice2(weight: Double, buyingPrice: Double): Double {
            // Placeholder implementation, replace with actual implementation
            val profitPercentage = 1.3 //profit will be 1.3%
            return buyingPrice * weight*profitPercentage
        }
    }
}