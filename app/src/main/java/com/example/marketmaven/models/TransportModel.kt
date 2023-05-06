package com.example.marketmaven.models

data class TransportModel(
    var transId: String? = null,
    var transItem: String? = null,
    var transItemWeight: String? = null,
    var transWeightFactor: String? = null,
    var transTotalWeightFactor: String? = null,
    var transPickUp: String? = null,
    var transDelivery: String? = null,
    var transDistance: String? = null,
    var transFuelEfficient: String? = null,
    var transFuelPrice: String? = null,
    var transTotalFuelEfficient: String? = null,
    var transTotalFuelCost: String? = null,
    var transDriverWage: String? = null,
    var transTotalCost: String? = null,
)
