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
import com.example.assistant.ViewModel.AdapterOceny
import com.example.assistant.ViewModel.SharedViewModel
import com.example.assistant.ViewModel.ViewModel_Main
import kotlinx.android.synthetic.main.fragment_student_det_oceny.view.*
import kotlinx.coroutines.InternalCoroutinesApi
@InternalCoroutinesApi
class fragment_student_det_oceny : Fragment() {

    private lateinit var mStudentViewModel: ViewModel_Main
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_student_det_oceny, container, false)
        Log.d("MOJE", "TUTAJ SIĘ POJAWIĄ OCENKI")




        // Recyclerview
        val adapter = AdapterOceny()
        val recyclerStudenci = view.recycler_oceny
        recyclerStudenci.adapter = adapter
        recyclerStudenci.layoutManager = LinearLayoutManager(requireContext())

        // ViewModel
        mStudentViewModel = ViewModelProvider(this).get(ViewModel_Main::class.java)
        mStudentViewModel.getOceny(sharedViewModel.curGroup.value!!, sharedViewModel.curStudent.value!!).observe(viewLifecycleOwner, Observer {
                ocena -> adapter.setData(ocena)
            Log.d("Takie mi wrzuciło", ocena.toString())
        })


        view.btn_nowa_ocena.setOnClickListener{
            val action = fragment_student_detailsDirections.actionFragmentStudentDetailsToFragmentDodajOcene(sharedViewModel.curStudent.value!!,sharedViewModel.curGroup.value!!)
            view.findNavController().navigate(action)
        }
        return view
    }

}