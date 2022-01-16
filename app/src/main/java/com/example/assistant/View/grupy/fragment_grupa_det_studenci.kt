package com.example.assistant

import android.os.Bundle
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
import com.example.assistant.ViewModel.ViewModel_Main
import kotlinx.android.synthetic.main.fragment_grupa_det_studenci.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
public class fragment_grupa_det_studenci() : Fragment(){

    private lateinit var currentGroup: Grupa
    private lateinit var mStudentViewModel: ViewModel_Main
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_grupa_det_studenci, container, false)
        currentGroup = sharedViewModel.curGroup.value!!

        // Recyclerview
        val adapter = AdapterStudenciWGrupie()
        val recyclerStudenci = view.recycler_studenciWGrupie
        recyclerStudenci.adapter = adapter
        recyclerStudenci.layoutManager = LinearLayoutManager(requireContext())

        // ViewModel
        mStudentViewModel = ViewModelProvider(this).get(ViewModel_Main::class.java)
        mStudentViewModel.getStudenciWGrupie(currentGroup).observe(viewLifecycleOwner, Observer {
                student -> adapter.setData(student)
        })



        view.btn_enrolment.setOnClickListener {
            val action = fragment_grupa_detailsDirections.actionFragmentGrupaDetailsToFragmentEnrolment(currentGroup)
            view.findNavController().navigate(action)
        }

        return view
    }


}