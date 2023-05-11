package com.example.marketmaven.validators

// This is an object that contains a method for validating input for a transport calculation

object TransportCalculateValidator {
    fun validateInput(transItem: String,  // the name of the item being transported
                      transItemWeight: String, // the weight of the item being transported
                      transItemWeightFactor: String, // the factor used to calculate the weight cost
                      transTotalFactor: String, // the total factor used to calculate the transport cost
                      transPickUp: String, // the location where the item is being picked up
                      transDelivary: String, // the location where the item is being delivered
                      transDistance: String, // the distance between the pickup and delivery locations
                      transFuelEfficient: String, // the fuel efficiency of the vehicle
                      transFuelPrice: String, // the price of fuel per gallon
                      transTotalFuelEfficient: String, // the total fuel efficiency of the vehicle
                      transTotalFuelCost: String, // the total cost of fuel for the trip
                      transDriverWage: String, // the wage of the driver
                      transTotalCost: String // the total cost of the transport
    ): Boolean {
        // This returns true if none of the input fields are empty or have a value less than or equal to 0
        return !(transItemWeight.toDouble() <= 0 ||
                transItem.isEmpty() ||
                transItemWeightFactor.toDouble() <= 0 ||
                transTotalFactor.toDouble() <= 0 ||
                transPickUp.isEmpty() ||
                transDelivary.isEmpty()||
                transDistance.toDouble() <= 0 ||
                transFuelEfficient.toDouble() <= 0 ||
                transFuelPrice.toDouble() <= 0 ||
                transTotalFuelEfficient.toDouble() <= 0 ||
                transTotalFuelCost.toDouble() <= 0 ||
                transDriverWage.toDouble() <= 0 ||
                transTotalCost.toDouble() <= 0)
    }
}