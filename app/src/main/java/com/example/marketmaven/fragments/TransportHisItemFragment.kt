package com.example.marketmaven.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.marketmaven.R
import com.example.marketmaven.activities.TransportHistory
import com.example.marketmaven.models.TransportModel
import com.google.firebase.database.FirebaseDatabase
import java.text.DateFormat
import java.util.Calendar

class TransportHisItemFragment : Fragment() {

    private lateinit var  iname: TextView
    private lateinit var  iweight: TextView
    private lateinit var  idate: TextView
    private lateinit var  iweightfactor: TextView
    private lateinit var  iweighttotalfactor: TextView
    private lateinit var  ipickup: TextView
    private lateinit var  idelivery: TextView
    private lateinit var  idistance: TextView
    private lateinit var  ifuelefficient: TextView
    private lateinit var  itotalfuelefficient: TextView
    private lateinit var  ifuelprice: TextView
    private lateinit var  itotalfuelcost: TextView
    private lateinit var  idriverwage: TextView
    private lateinit var  itotalcost: TextView
    private lateinit var  btnUpdate: ImageButton
    private lateinit var  btnDelete: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_transport_his_item, container, false)

        initView(view)
        setValuesToViews()


        btnDelete.setOnClickListener{
            val transId = arguments?.getString("transId")
            if (transId != null) {
                deleteRecord(transId)
            }
        }

        btnUpdate.setOnClickListener {
            val transId = arguments?.getString("transId")
            val transItem = arguments?.getString("transItem")
            if (transId != null && transItem != null){
                openUpdateDialog(transId, transItem)
            }
        }



        return view
    }

    private fun initView(view: View){
        iname = view.findViewById(R.id.iname)
        iweight = view.findViewById(R.id.iweight)
        idate = view.findViewById(R.id.idate)
        iweightfactor = view.findViewById(R.id.iweightfactor)
        iweighttotalfactor = view.findViewById(R.id.iweighttotalfactor)
        ipickup = view.findViewById(R.id.ipickup)
        idelivery = view.findViewById(R.id.idelivary)
        idistance = view.findViewById(R.id.idistance)
        ifuelefficient = view.findViewById(R.id.ifuelefficient)
        itotalfuelefficient = view.findViewById(R.id.itotalfuelefficient)
        ifuelprice = view.findViewById(R.id.ifuelprice)
        itotalfuelcost = view.findViewById(R.id.itotalfuelcost)
        idriverwage = view.findViewById(R.id.idriverwage)
        itotalcost = view.findViewById(R.id.itotalcost)
        btnDelete = view.findViewById(R.id.del_btn)
        btnUpdate= view.findViewById(R.id.update_btn)
    }

    private fun deleteRecord(
        transId: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Transport").child(transId)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(requireContext(), "Delete Transport Data", Toast.LENGTH_LONG).show()
            val fragment = TransportHistoryFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainerView, fragment, null)?.addToBackStack(null)?.commit()
        }.addOnFailureListener{ error ->
            Toast.makeText(requireContext(), "Delete Error ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun setValuesToViews(){
        iname.text = this.arguments?.getString("transItem")
        iweight.text = this.arguments?.getString("transWeight")
        idate.text = this.arguments?.getString("transDate")
        iweightfactor.text = this.arguments?.getString("transWeightFactor")
        iweighttotalfactor.text = this.arguments?.getString("transTotalWeightFactor")
        ipickup.text = this.arguments?.getString("transPickUp")
        idelivery.text = this.arguments?.getString("transDelivery")
        idistance.text = this.arguments?.getString("transDistance")
        ifuelefficient.text = this.arguments?.getString("transFuelEfficient")
        itotalfuelefficient.text = this.arguments?.getString("transTotalFuelEfficient")
        ifuelprice.text = this.arguments?.getString("transFuelPrice")
        itotalfuelcost.text = this.arguments?.getString("transTotalFuelCost")
        idriverwage.text = this.arguments?.getString("transDriverWage")
        itotalcost.text = this.arguments?.getString("transTotalCost")

    }


    private fun openUpdateDialog(
        transId: String,
        transItem: String
    ) {
        val mDialog = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.updatetransport, null)

        mDialog.setView(mDialogView)

        val edtname = mDialogView.findViewById<EditText>(R.id.edtname)
        val edtweight = mDialogView.findViewById<EditText>(R.id.edtweight)
        val edtweightfactor = mDialogView.findViewById<EditText>(R.id.edtweightfactor)
        val edtpickup = mDialogView.findViewById<EditText>(R.id.edtpickup)
        val edtdelivery = mDialogView.findViewById<EditText>(R.id.edtdelivery)
        val edtdistance = mDialogView.findViewById<EditText>(R.id.edtdistance)
        val edtfuelEfficient = mDialogView.findViewById<EditText>(R.id.edtfuelEfficient)
        val edtfuelPrice = mDialogView.findViewById<EditText>(R.id.edtfuelprice)
        val edtdriverWage = mDialogView.findViewById<EditText>(R.id.edtdriverwage)
        val transUpdateBtn = mDialogView.findViewById<Button>(R.id.transUpdateBtn)

        edtname.setText(this.arguments?.getString("transItem"))
        edtweight.setText(this.arguments?.getString("transWeight"))
        edtweightfactor.setText(this.arguments?.getString("transWeightFactor"))
        edtpickup.setText(this.arguments?.getString("transPickUp"))
        edtdelivery.setText(this.arguments?.getString("transDelivery"))
        edtdistance.setText(this.arguments?.getString("transDistance"))
        edtfuelEfficient.setText(this.arguments?.getString("transFuelEfficient"))
        edtfuelPrice.setText(this.arguments?.getString("transFuelPrice"))
        edtdriverWage.setText(this.arguments?.getString("transDriverWage"))



        val alertDialog = mDialog.create()
        alertDialog.show()

        transUpdateBtn.setOnClickListener {
            updateTransData(
                transId,
                edtname.text.toString(),
                edtweight.text.toString(),
                edtweightfactor.text.toString(),
                edtpickup.text.toString(),
                edtdelivery.text.toString(),
                edtdistance.text.toString(),
                edtfuelEfficient.text.toString(),
                edtfuelPrice.text.toString(),
                edtdriverWage.text.toString(),
            )

            Toast.makeText(context, "Employee Data Updated", Toast.LENGTH_LONG).show()



            iname.text = edtname.text.toString()
            iweight.text = edtweight.text.toString()
            iweightfactor.text = edtweightfactor.text.toString()
            ipickup.text = edtpickup.text.toString()
            idelivery.text = edtdelivery.text.toString()
            idistance.text = edtdistance.text.toString()
            ifuelefficient.text = edtfuelEfficient.text.toString()
            ifuelprice.text = edtfuelPrice.text.toString()
            idriverwage.text = edtdriverWage.text.toString()


            alertDialog.dismiss()
        }
    }

    private fun updateTransData(
        transId: String,
        transItem: String,
        transItemWeight: String,
        transWeightFactor: String,
        transPickUp: String,
        transDelivery: String,
        transDistance: String,
        transFuelEfficient: String,
        transFuelPrice: String,
        transDriverWage: String,
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Transport").child(transId)


        val calender = Calendar.getInstance().time
        val transDate = DateFormat.getDateInstance().format(calender)
        val transTotalFuelEfficient = "%.2f".format(1 / transFuelEfficient.toDouble())
        val transTotalFuelCost = "%.2f".format( (transDistance.toDouble() / transFuelEfficient.toDouble()) * transFuelPrice.toDouble())
        val transTotalWeightFactor = "%.2f".format(transItemWeight.toDouble() * transWeightFactor.toDouble())
        val transTotalCost = "%.2f".format((transTotalFuelCost.toDouble() + transDriverWage.toDouble() + transTotalWeightFactor.toDouble()))

        iweighttotalfactor.text = transTotalWeightFactor
        itotalfuelefficient.text = transTotalFuelEfficient
        itotalfuelcost.text = transTotalFuelCost
        itotalcost.text = transTotalCost

        val transInfo = TransportModel(transId, transItem, transDate,
            "%.2f".format(transItemWeight.toDouble()),
            "%.2f".format(transWeightFactor.toDouble()),
            "%.2f".format(transTotalWeightFactor.toDouble()),
            transPickUp, transDelivery,
            "%.2f".format(transDistance.toDouble()),
            "%.2f".format(transFuelEfficient.toDouble()),
            "%.2f".format(transFuelPrice.toDouble()), transTotalFuelEfficient, transTotalFuelCost,
            "%.2f".format(transDriverWage.toDouble()),
            transTotalCost)

        dbRef.setValue(transInfo)
    }

}