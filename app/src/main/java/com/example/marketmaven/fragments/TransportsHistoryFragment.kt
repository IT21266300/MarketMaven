package com.example.marketmaven.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketmaven.R
import com.example.marketmaven.adapters.TransportAdapter
import com.example.marketmaven.models.TransportModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TransportsHistoryFragment : Fragment() {

    private lateinit var transHisRecycle: RecyclerView // declare RecyclerView instance variable
    private lateinit var  txtLoading: TextView // declare TextView instance variable
    private lateinit var transHisList: ArrayList<TransportModel> // declare ArrayList instance variable
    private lateinit var dbRef: DatabaseReference // declare DatabaseReference instance variable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_transport_history, container, false)

        // initialize RecyclerView variable with its corresponding ID from the view
        transHisRecycle = view.findViewById(R.id.transHisRecycle)

        // set LinearLayoutManager as layout manager for the RecyclerView
        transHisRecycle.layoutManager = LinearLayoutManager(requireContext())

        // set fixed size for RecyclerView
        transHisRecycle.setHasFixedSize(true)

        // set fixed size for RecyclerView
        txtLoading = view.findViewById(R.id.loadingHis)

        // initialize ArrayList variable
        transHisList = arrayListOf<TransportModel>()

        // call a function to get transport history data
        getTransportHistory()


        return view
    }

    private fun  getTransportHistory(){
        transHisRecycle.visibility = View.GONE
        txtLoading.visibility = View.VISIBLE

        // Get a reference to the "Transport" node in the Firebase Realtime Database
        dbRef = FirebaseDatabase.getInstance().getReference("Transport")

        // Add a ValueEventListener to the reference
        dbRef.addValueEventListener(object : ValueEventListener {

            // Clear the existing list of transport history
            override fun onDataChange(snapshot: DataSnapshot) {
                transHisList.clear()

                // Check if the snapshot exists
                if(snapshot.exists()){
                    // Loop through the children of the snapshot and add the transport data to the list
                    for (transSnap in snapshot.children){
                        val transData =  transSnap.getValue(TransportModel::class.java)
                        transHisList.add(transData!!)
                    }

                    // Create a new TransportAdapter with the list of transport history
                    val transHisAdapter = TransportAdapter(transHisList)
                    transHisRecycle.adapter = transHisAdapter

                    // Set an item click listener for the adapter
                    transHisAdapter.setOnItemClickListener(object : TransportAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            // Create a bundle and add the transport data as extras
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

                            // Create a new instance of the TransportsHisItemFragment and pass the bundle as arguments
                            val fragment = TransportsHisItemFragment()
                            fragment.arguments = bundle
                            // Replace the current fragment with the new fragment and add it to the back stack
                            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, fragment, null)?.addToBackStack(null)?.commit()
                        }

                    })

                    // Show the RecyclerView and hide the loading message
                    transHisRecycle.visibility = View.VISIBLE
                    txtLoading.visibility = View.GONE
                }
            }

            // This function is called when the ValueEventListener is cancelled
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}