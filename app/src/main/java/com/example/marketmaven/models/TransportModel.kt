package com.example.marketmaven.models

data class TransportModel(
    var transId: String? = null,
    var transItem: String? = null,
    var transItemWeight: String? = null,
    var transPickUp: String? = null,
    var transDelivery: String? = null,
    var transDistance: String? = null,
    var transFuelEfficient: String? = null,
    var transFuelPrice: Double? = null,
    var transTotalFuelEfficient: Double? = null,
    var transTotalFuelCost: Double? = null,
    var transDriverWage: Double? = null,
    var transTotalCost: Double? = null
)
