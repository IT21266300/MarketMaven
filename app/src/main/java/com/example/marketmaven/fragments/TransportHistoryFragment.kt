package com.example.marketmaven.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketmaven.R
import com.example.marketmaven.activities.TransportHisItem
import com.example.marketmaven.adapters.TransportAdapter
import com.example.marketmaven.models.TransportModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TransportHistoryFragment : Fragment() {

    private lateinit var transHisRecycle: RecyclerView
    private lateinit var  txtLoading: TextView
    private lateinit var transHisList: ArrayList<TransportModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_transport_history, container, false)
        transHisRecycle = view.findViewById(R.id.transHisRecycle)
        transHisRecycle.layoutManager = LinearLayoutManager(requireContext())
        transHisRecycle.setHasFixedSize(true)
        txtLoading = view.findViewById(R.id.loadingHis)

        transHisList = arrayListOf<TransportModel>()

        getTransportHistory()


        return view
    }

    private fun  getTransportHistory(){
        transHisRecycle.visibility = View.GONE
        txtLoading.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Transport")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                transHisList.clear()
                if(snapshot.exists()){
                    for (transSnap in snapshot.children){
                        val transData =  transSnap.getValue(TransportModel::class.java)
                        transHisList.add(transData!!)
                    }
                    val transHisAdapter = TransportAdapter(transHisList)
                    transHisRecycle.adapter = transHisAdapter

                    transHisAdapter.setOnItemClickListener(object : TransportAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val bundle = Bundle()

                            //put extras
                            bundle.putString("transId", transHisList[position].transId)
                            bundle.putString("transItem", transHisList[position].transItem)
                            bundle.putString("transDate", transHisList[position].transDate)
                            bundle.putString("transWeight", transHisList[position].transItemWeight)
                            bundle.putString("transWeightFactor", transHisList[position].transWeightFactor)
                            bundle.putString("transTotalWeightFactor", transHisList[position].transTotalWeightFactor)
                            bundle.putString("transPickUp", transHisList[position].transPickUp)
                            bundle.putString("transDelivery", transHisList[position].transDelivery)
                            bundle.putString("transDistance", transHisList[position].transDistance)
                            bundle.putString("transFuelEfficient", transHisList[position].transFuelEfficient)
                            bundle.putString("transTotalFuelEfficient", transHisList[position].transTotalFuelEfficient)
                            bundle.putString("transFuelPrice", transHisList[position].transFuelPrice)
                            bundle.putString("transTotalFuelCost", transHisList[position].transTotalFuelCost)
                            bundle.putString("transDriverWage", transHisList[position].transDriverWage)
                            bundle.putString("transTotalCost", transHisList[position].transTotalCost)

                            val fragment = TransportHisItemFragment()
                            fragment.arguments = bundle
                            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, fragment, null)?.addToBackStack(null)?.commit()
                        }

                    })

                    transHisRecycle.visibility = View.VISIBLE
                    txtLoading.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}