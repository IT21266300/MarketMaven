package font.validators

object TransportValidator {
    fun validateInput(transItem: String,
                      transItemWeight: String,
                      transItemWeightFactor: String,
                      transTotalFactor: String,
                      transPickUp: String,
                      transDelivary: String,
                      transDistance: String,
                      transFuelEfficient: String,
                      transFuelPrice: String,
                      transTotalFuelEfficient: String,
                      transTotalFuelCost: String,
                      transDriverWage: String,
                      transTotalCost: String
    ): Boolean {
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