package com.example.marketmaven

data class TransportModel(
    var transId: String? = null,
    var transItem: String? = null,
    var transItemWeight: String? = null,
    var transPickUp: String? = null,
    var transDelivery: String? = null,
    var transDistance: String? = null
)
