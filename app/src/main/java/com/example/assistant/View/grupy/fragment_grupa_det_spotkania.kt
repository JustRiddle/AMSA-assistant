package com.example.assistant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class fragment_grupa_det_spotkania : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var spotkaniaAdapter: AdapterSpotkania
    lateinit var SplayoutManager:LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        spotkaniaAdapter=(activity as MainActivity).SpotkaniaAdapter.apply {
            obecnosc = ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grupa_det_spotkania, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (view.findViewById<FloatingActionButton>(R.id.btn_nowe_spotkanie)).setOnClickListener{
            it.findNavController().navigate(R.id.action_fragment_grupa_details_to_fragment_dodaj_spotkanie)
        }

        SplayoutManager= LinearLayoutManager(context)
        view.findViewById<RecyclerView>(R.id.recycler_spotkania).apply {
            adapter=spotkaniaAdapter
            layoutManager=SplayoutManager
        }
    }




}