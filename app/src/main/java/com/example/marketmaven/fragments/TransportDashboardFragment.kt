package com.example.marketmaven.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.marketmaven.R
import com.example.marketmaven.activities.ItemList
import com.example.marketmaven.activities.TransportData
import com.example.marketmaven.activities.TransportHistory

class TransportDashboardFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_transportdashboard, container, false)

        val calTransport = view.findViewById<Button>(R.id.cal_trans_btn)
        calTransport.setOnClickListener{
            val fragment = itemListFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainerView, fragment, null)?.addToBackStack(null)?.commit()
        }

        val btnTranHis = view.findViewById<Button>(R.id.trans_his_btn)
        btnTranHis.setOnClickListener{
            val fragment = TransportHistoryFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainerView, fragment, null)?.addToBackStack(null)?.commit()
        }

        val btnTranData = view.findViewById<Button>(R.id.trans_price)
        btnTranData.setOnClickListener{
            val intent3 = Intent(requireContext(), TransportData::class.java)
            startActivity(intent3)
        }

        return view
    }


}