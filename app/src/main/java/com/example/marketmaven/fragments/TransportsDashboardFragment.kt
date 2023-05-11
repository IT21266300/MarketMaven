package com.example.marketmaven.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.marketmaven.R
import com.example.marketmaven.activities.TransportData

class TransportsDashboardFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_transportdashboard, container, false)

        // Set up onClickListener for the "Calculate Transportation" button
        val calTransport = view.findViewById<Button>(R.id.cal_trans_btn)
        calTransport.setOnClickListener{

            // Replace the current fragment with a new instance of the ItemListFragment
            val fragment = ItemListFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainerView, fragment, null)?.addToBackStack(null)?.commit()
        }

        // Set up onClickListener for the "Transportation History" button
        val btnTranHis = view.findViewById<Button>(R.id.trans_his_btn)
        btnTranHis.setOnClickListener{
            // Replace the current fragment with a new instance of the TransportsHistoryFragment
            val fragment = TransportsHistoryFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainerView, fragment, null)?.addToBackStack(null)?.commit()
        }

        // Set up onClickListener for the "Transportation Data" button
        val btnTranData = view.findViewById<Button>(R.id.trans_price)
        btnTranData.setOnClickListener{
            // Create an intent to start the TransportData activity
            val intent3 = Intent(requireContext(), TransportData::class.java)
            startActivity(intent3)
        }

        return view
    }


}