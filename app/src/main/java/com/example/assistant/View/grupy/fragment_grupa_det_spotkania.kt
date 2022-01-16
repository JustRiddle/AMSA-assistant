package com.example.assistant

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assistant.Model.Grupa
import com.example.assistant.ViewModel.SharedViewModel
import com.example.assistant.ViewModel.ViewModel_Main
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_grupa_det_spotkania.view.*
import kotlinx.android.synthetic.main.fragment_grupa_det_studenci.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class fragment_grupa_det_spotkania : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var currentGroup: Grupa
    private lateinit var mStudentViewModel: ViewModel_Main


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_grupa_det_spotkania, container, false)
        currentGroup = sharedViewModel.curGroup.value!!
        Log.d("MOJE" ,"ZACZYNAM")
        // Recyclerview
        val adapter = AdapterSpotkania()
        val recyclerStudenci = view.recycler_spotkania
        recyclerStudenci.adapter = adapter
        recyclerStudenci.layoutManager = LinearLayoutManager(requireContext())

        // ViewModel
        mStudentViewModel = ViewModelProvider(this).get(ViewModel_Main::class.java)
        mStudentViewModel.getSpotkania(currentGroup).observe(viewLifecycleOwner, Observer {
                spotkania -> adapter.setData(spotkania)
        })

        Log.d("MOJE" ,"Koncze")

        view.btn_nowe_spotkanie.setOnClickListener {
            val action = fragment_grupa_detailsDirections.actionFragmentGrupaDetailsToFragmentDodajSpotkanie(currentGroup)
            view.findNavController().navigate(action)
        }
        return view
    }

}