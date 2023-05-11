package com.example.marketmaven.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.marketmaven.R

class HomesFragment : Fragment() {
    private lateinit var btnTransport: Button

    // This is the onCreateView() method, which is called when the fragment's view is created
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // This inflates the layout file for the fragment, creating a view hierarchy
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // This finds the transport button view in the layout hierarchy and sets an onClickListener
        btnTransport = view.findViewById(R.id.transportBtn)
        btnTransport.setOnClickListener{
            // This creates a new instance of TransportsDashboardFragment and replaces the current fragment with it
            val fragment = TransportsDashboardFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainerView, fragment, null)?.addToBackStack(null)?.commit()
        }

        // This returns the root view of the fragment's view hierarchy
        return view
    }


}

