package com.example.marketmaven.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.marketmaven.R
import com.example.marketmaven.activities.TransportDashboard
import com.example.marketmaven.activities.TransportHistory
import com.example.marketmaven.models.TransportModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.DateFormat
import java.util.Calendar

class caltransFragment : Fragment() {

    lateinit var itemName: EditText
    lateinit var itemWeight: EditText
    lateinit var itemWeightFactor: EditText
    lateinit var pickupLocation: EditText
    lateinit var deliveryLocation: EditText
    lateinit var distance: EditText
    lateinit var fuelEfficient: EditText
    lateinit var fuelPrice: EditText
    lateinit var driverWage: EditText
    private lateinit var calculateBtn: Button
    private lateinit var cancelBtn: Button

    lateinit var dbRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_cal_trans, container, false)

        itemName = view.findViewById(R.id.itemName)
        itemWeight = view.findViewById(R.id.edt_weight)
        itemWeightFactor = view.findViewById(R.id.edt_weight_factor)
        pickupLocation = view.findViewById(R.id.edt_pickup)
        deliveryLocation = view.findViewById(R.id.edt_delivery)
        distance = view.findViewById(R.id.edt_distance)
        fuelEfficient = view.findViewById(R.id.edt_fuelEfficient)
        fuelPrice = view.findViewById(R.id.edt_fuelPrice)
        driverWage = view.findViewById(R.id.edt_driverWage)
        cancelBtn = view.findViewById(R.id.cancelBtn)
        calculateBtn = view.findViewById(R.id.submitBtn)


        val args = this.arguments
        val inputs = args?.getString("iname")
        itemName.setText(inputs)

        dbRef = FirebaseDatabase.getInstance().getReference("Transport")

        calculateBtn.setOnClickListener {
            saveTransportData()
        }

        cancelBtn.setOnClickListener {
            val fragment = TransportDashboardFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainerView, fragment, null)?.addToBackStack(null)?.commit()
        }

        return view

    }

    fun saveTransportData(){
        val transItem = itemName.text.toString()
        val transItemWeight = itemWeight.text.toString()
        val transWeightFactor = itemWeightFactor.text.toString()
        val transPickUp = pickupLocation.text.toString()
        val transDelivery = deliveryLocation.text.toString()
        val transDistance = distance.text.toString()
        val transFuelEfficient = fuelEfficient.text.toString()
        val transFuelPrice = fuelPrice.text.toString()
        val transDriverWage = driverWage.text.toString()

        if(transItem.isEmpty()){
            itemName.error = "Transport Item Required!"
            itemName.requestFocus()
            return
        }

        if(transItemWeight.isEmpty() || transItemWeight.toDouble() <= 0){
            itemWeight.error = "Please Enter Valid Item Weight!"
            itemName.requestFocus()
            return
        }

        if(transWeightFactor.isEmpty() || transWeightFactor.toDouble() <= 0){
            itemWeightFactor.error = "Please Enter Valid Weight Factor!"
            itemName.requestFocus()
            return
        }

        if(transPickUp.isEmpty()){
            pickupLocation.error = "Pickup Location Required!"
            itemName.requestFocus()
            return
        }

        if(transDelivery.isEmpty()){
            deliveryLocation.error = "Delivery Location Required!"
            itemName.requestFocus()
            return
        }

        if(transDistance.isEmpty() || transDistance.toDouble() <= 0){
            distance.error = "Please Enter Valid Distance!"
            itemName.requestFocus()
            return
        }

        if(transFuelEfficient.isEmpty() || transFuelEfficient.toDouble() <= 0){
            fuelEfficient.error = "Please Enter Valid Fuel Efficient!"
            itemName.requestFocus()
            return
        }

        if(transFuelPrice.isEmpty() || transFuelPrice.toDouble() <= 0){
            fuelPrice.error = "Please Enter Valid Fuel Price!"
            itemName.requestFocus()
            return
        }

        if(transDriverWage.isEmpty() || transDriverWage.toDouble() <= 0){
            driverWage.error = "Please Enter Valid Driver Wage!"
            itemName.requestFocus()
            return
        }


        val calender = Calendar.getInstance().time
        val transDate = DateFormat.getDateInstance().format(calender)
        val transTotalFuelEfficient = "%.2f".format(1 / transFuelEfficient.toDouble())
        val transTotalFuelCost = "%.2f".format( (transDistance.toDouble() / transFuelEfficient.toDouble()) * transFuelPrice.toDouble())
        val transTotalWeightFactor = "%.2f".format( transWeightFactor.toDouble() * transItemWeight.toDouble())
        val transTotalCost = "%.2f".format((transTotalFuelCost.toDouble() + transDriverWage.toDouble() + transTotalWeightFactor.toDouble()))

        val transId = dbRef.push().key!!
        val transport = TransportModel(transId, transItem, transDate,
            "%.2f".format(transItemWeight.toDouble()),
            "%.2f".format(transWeightFactor.toDouble()),
            "%.2f".format(transTotalWeightFactor.toDouble()),
            transPickUp, transDelivery,
            "%.2f".format(transDistance.toDouble()),
            "%.2f".format(transFuelEfficient.toDouble()),
            "%.2f".format(transFuelPrice.toDouble()), transTotalFuelEfficient, transTotalFuelCost,
            "%.2f".format(transDriverWage.toDouble()),
            transTotalCost)
        dbRef.child(transId).setValue(transport).addOnCompleteListener{
            Toast.makeText(requireContext(), "New Transport Add Successfully", Toast.LENGTH_LONG).show()
            val fragment = TransportHistoryFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainerView, fragment, null)?.addToBackStack(null)?.commit()

        }.addOnFailureListener{ err ->
            Toast.makeText(requireContext(), "Error ${err.message}", Toast.LENGTH_LONG).show()
        }

    }

}