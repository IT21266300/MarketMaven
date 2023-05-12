package com.example.marketmaven.validators

import com.google.common.truth.Truth.assertThat
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import javax.xml.validation.Validator

@RunWith(JUnit4::class)
class TransportCalculateValidatorTest{

    @Test
    fun whenInputDataIsValid(){
        val transItem = "Potato"
        val transItemWeight = "20"
        val transItemWeightFactor = "10"
        val transTotalFactor = "200"
        val transPickUp= "Welimada"
        val transDelivary= "Colombo"
        val transDistance = "300"
        val transFuelEfficient= "20"
        val transFuelPrice= "320"
        val transTotalFuelEfficient= "0.34"
        val transTotalFuelCost = "1200"
        val transDriverWage = "1000"
        val transTotalCost = "400"
        val result = TransportCalculateValidator.validateInput(transItem,
            transItemWeight,
            transItemWeightFactor,
            transTotalFactor,
            transPickUp,
            transDelivary,
            transDistance,
            transFuelEfficient,
            transFuelPrice,
            transTotalFuelEfficient,
            transTotalFuelCost,
            transDriverWage,
            transTotalCost)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenInputDataIsInvalid(){
        val transItem = ""
        val transItemWeight = "-1"
        val transItemWeightFactor = "10"
        val transTotalFactor = "200"
        val transPickUp= "Welimada"
        val transDelivary= "Colombo"
        val transDistance = "300"
        val transFuelEfficient= "20"
        val transFuelPrice= "320"
        val transTotalFuelEfficient= "0.34"
        val transTotalFuelCost = "1200"
        val transDriverWage = "1000"
        val transTotalCost = "400"
        val result = TransportCalculateValidator.validateInput(transItem,
            transItemWeight,
            transItemWeightFactor,
            transTotalFactor,
            transPickUp,
            transDelivary,
            transDistance,
            transFuelEfficient,
            transFuelPrice,
            transTotalFuelEfficient,
            transTotalFuelCost,
            transDriverWage,
            transTotalCost)
        assertThat(result).isEqualTo(false)
    }

}