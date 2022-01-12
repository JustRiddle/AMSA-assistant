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
import com.example.assistant.Model.Grupa
import com.example.assistant.ViewModel.SharedViewModel
import com.example.assistant.ViewModel.ViewModel_grupy
import com.example.assistant.ViewModel.ViewModel_studenci
import kotlinx.android.synthetic.main.fragment_grupa_det_studenci.view.*
import kotlinx.android.synthetic.main.fragment_lista_grupy.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
public class fragment_grupa_det_studenci() : Fragment(){

    private lateinit var currentGroup: Grupa
    private lateinit var mstudenciViewModel: ViewModel_studenci
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_grupa_det_studenci, container, false)
        currentGroup = sharedViewModel.curGroup.value!!

        //Recycler
//        val adapter = AdapterStudenci()
//        val recyclerGrupy = view.recycler_grupy
//        recyclerGrupy.adapter = adapter
//        recyclerGrupy.layoutManager = LinearLayoutManager(requireContext())
//
//        // ViewModel
        mstudenciViewModel = ViewModelProvider(this).get(ViewModel_studenci::class.java)
//        mstudenciViewModel.getGroupStudents(currentGroup).observe(viewLifecycleOwner, Observer {
//                grupa -> adapter.setData(grupa)
//        })
        mstudenciViewModel.getGroupStudents(currentGroup)
        mstudenciViewModel.returnedVal.observe(viewLifecycleOwner, Observer {
            Log.d("CROSS", mstudenciViewModel.returnedVal.value!![0].grupa.Nazwa )

        })
//


        view.btn_enrolment.setOnClickListener {
            val action = fragment_grupa_detailsDirections.actionFragmentGrupaDetailsToFragmentEnrolment(currentGroup)
            view.findNavController().navigate(action)
        }



        return view
    }


}