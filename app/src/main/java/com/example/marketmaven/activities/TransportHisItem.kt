package com.example.marketmaven.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class TransportHisItem : AppCompatActivity() {

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transporthisitem)

        initView()
        setValuesToViews()

        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("transId").toString()
            )
        }

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("transId").toString(),
                intent.getStringExtra("transItem").toString()
            )
        }

    }

    private fun deleteRecord(
        transId: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Transport").child(transId)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Delete Transport Data", Toast.LENGTH_LONG).show()
            val intent = Intent(this, TransportHistory::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Delete Error ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView(){
        iname = findViewById(R.id.iname)
        iweight = findViewById(R.id.iweight)
        idate = findViewById(R.id.idate)
        iweightfactor = findViewById(R.id.iweightfactor)
        iweighttotalfactor = findViewById(R.id.iweighttotalfactor)
        ipickup = findViewById(R.id.ipickup)
        idelivery = findViewById(R.id.idelivary)
        idistance = findViewById(R.id.idistance)
        ifuelefficient = findViewById(R.id.ifuelefficient)
        itotalfuelefficient = findViewById(R.id.itotalfuelefficient)
        ifuelprice = findViewById(R.id.ifuelprice)
        itotalfuelcost = findViewById(R.id.itotalfuelcost)
        idriverwage = findViewById(R.id.idriverwage)
        itotalcost = findViewById(R.id.itotalcost)
        btnDelete = findViewById(R.id.del_btn)
        btnUpdate= findViewById(R.id.update_btn)
    }

    private fun setValuesToViews(){
        iname.text = intent.getStringExtra("transItem")
        iweight.text = intent.getStringExtra("transWeight")
        idate.text = intent.getStringExtra("transDate")
        iweightfactor.text = intent.getStringExtra("transWeightFactor")
        iweighttotalfactor.text = intent.getStringExtra("transTotalWeightFactor")
        ipickup.text = intent.getStringExtra("transPickUp")
        idelivery.text = intent.getStringExtra("transDelivery")
        idistance.text = intent.getStringExtra("transDistance")
        ifuelefficient.text = intent.getStringExtra("transFuelEfficient")
        itotalfuelefficient.text = intent.getStringExtra("transTotalFuelEfficient")
        ifuelprice.text = intent.getStringExtra("transFuelPrice")
        itotalfuelcost.text = intent.getStringExtra("transTotalFuelCost")
        idriverwage.text = intent.getStringExtra("transDriverWage")
        itotalcost.text = intent.getStringExtra("transTotalCost")

    }

    private fun openUpdateDialog(
        transId: String,
        transItem: String
    ) {
        val mDialog = AlertDialog.Builder(this)
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

        edtname.setText(intent.getStringExtra("transItem").toString())
        edtweight.setText(intent.getStringExtra("transWeight").toString())
        edtweightfactor.setText(intent.getStringExtra("transWeightFactor").toString())
        edtpickup.setText(intent.getStringExtra("transPickUp").toString())
        edtdelivery.setText(intent.getStringExtra("transDelivery").toString())
        edtdistance.setText(intent.getStringExtra("transDistance").toString())
        edtfuelEfficient.setText(intent.getStringExtra("transFuelEfficient").toString())
        edtfuelPrice.setText(intent.getStringExtra("transFuelPrice").toString())
        edtdriverWage.setText(intent.getStringExtra("transDriverWage").toString())



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

            Toast.makeText(applicationContext, "Employee Data Updated", Toast.LENGTH_LONG).show()



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