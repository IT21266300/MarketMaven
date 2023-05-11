package com.example.marketmaven.fragments

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
import com.example.marketmaven.models.TransportModel
import com.google.firebase.database.FirebaseDatabase
import java.text.DateFormat
import java.util.Calendar

// This is a Fragment that displays the details of a single transport item from the history list.
class TransportsHisItemFragment : Fragment() {

    // Declare UI elements
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

    // Declare UI elements
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_transport_his_item, container, false)

        // Initialize UI elements
        initView(view)

        // Set the values of UI elements based on the transport item data
        setValuesToViews()


        // Handle click on the Delete button
        btnDelete.setOnClickListener{
            val transId = arguments?.getString("transId")
            if (transId != null) {
                deleteRecord(transId)
            }
        }

        // Handle click on the Update button
        btnUpdate.setOnClickListener {
            val transId = arguments?.getString("transId")
            val transItem = arguments?.getString("transItem")
            if (transId != null && transItem != null){
                // Show a dialog to allow the user to update the transport item data
                openUpdateDialog(transId, transItem)
            }
        }



        return view
    }

    // Initialize the UI elements
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
        // Get a reference to the "Transport" node in the Firebase database, and specify the child node to be deleted
        val dbRef = FirebaseDatabase.getInstance().getReference("Transport").child(transId)

        // Remove the value at the specified location in the database and get a task that represents the operation
        val mTask = dbRef.removeValue()

        // Set a listener to be called when the task is successfully completed
        mTask.addOnSuccessListener {
            // Show a toast to indicate that the transport data was successfully deleted
            Toast.makeText(requireContext(), "Delete Transport Data", Toast.LENGTH_LONG).show()

            // Create a new instance of the "TransportsHistoryFragment" and replace the current fragment with it
            val fragment = TransportsHistoryFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainerView, fragment, null)?.addToBackStack(null)?.commit()
        }// Set a listener to be called when the task fails
            .addOnFailureListener{ error ->
                // Show a toast to indicate that the delete operation failed, along with the error message
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


    /**
     * Opens an alert dialog for updating transport data.
     * @param transId the ID of the transport record to be updated
     * @param transItem the name of the transport item to be updated
     */
    private fun openUpdateDialog(
        transId: String,
        transItem: String
    ) {
        // Create an alert dialog
        val mDialog = AlertDialog.Builder(requireContext())

        // Inflate the layout for the dialog
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.updatetransport, null)

        // Set the view for the dialog
        mDialog.setView(mDialogView)

        // Get references to the EditText views in the dialog
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

        // Set the values of the EditText views to the current transport data
        edtname.setText(this.arguments?.getString("transItem"))
        edtweight.setText(this.arguments?.getString("transWeight"))
        edtweightfactor.setText(this.arguments?.getString("transWeightFactor"))
        edtpickup.setText(this.arguments?.getString("transPickUp"))
        edtdelivery.setText(this.arguments?.getString("transDelivery"))
        edtdistance.setText(this.arguments?.getString("transDistance"))
        edtfuelEfficient.setText(this.arguments?.getString("transFuelEfficient"))
        edtfuelPrice.setText(this.arguments?.getString("transFuelPrice"))
        edtdriverWage.setText(this.arguments?.getString("transDriverWage"))


        // Create and show the alert dialog
        val alertDialog = mDialog.create()
        alertDialog.show()

        // Set an OnClickListener for the Update button in the dialog
        transUpdateBtn.setOnClickListener {
            // Call the updateTransData function with the updated transport data
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

            // Show a toast message to indicate that the data has been updated
            Toast.makeText(context, "Employee Data Updated", Toast.LENGTH_LONG).show()


            // Update the values of the transport data displayed on the UI
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
        // Get a reference to the "Transport" node in the Firebase database with the given transport ID
        val dbRef = FirebaseDatabase.getInstance().getReference("Transport").child(transId)


        // Get the current date and format it using the default date format
        val calender = Calendar.getInstance().time
        val transDate = DateFormat.getDateInstance().format(calender)

        // Calculate the total fuel efficiency of the transport
        val transTotalFuelEfficient = "%.2f".format(1 / transFuelEfficient.toDouble())

        // Calculate the total fuel cost of the transport
        val transTotalFuelCost = "%.2f".format( (transDistance.toDouble() / transFuelEfficient.toDouble()) * transFuelPrice.toDouble())

        // Calculate the total weight factor of the transport
        val transTotalWeightFactor = "%.2f".format(transItemWeight.toDouble() * transWeightFactor.toDouble())

        // Calculate the total cost of the transport
        val transTotalCost = "%.2f".format((transTotalFuelCost.toDouble() + transDriverWage.toDouble() + transTotalWeightFactor.toDouble()))

        // Set the text of some UI elements to display the calculated values
        iweighttotalfactor.text = transTotalWeightFactor
        itotalfuelefficient.text = transTotalFuelEfficient
        itotalfuelcost.text = transTotalFuelCost
        itotalcost.text = transTotalCost

        // Create a TransportModel object with the calculated values and other transport data
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

        // Set the value of the "Transport" node in the Firebase database with the TransportModel object
        dbRef.setValue(transInfo)
    }

}