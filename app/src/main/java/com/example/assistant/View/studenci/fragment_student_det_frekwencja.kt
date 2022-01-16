package com.example.assistant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assistant.ViewModel.SharedViewModel
import com.example.assistant.ViewModel.ViewModel_Main
import kotlinx.android.synthetic.main.fragment_grupa_det_spotkania.view.*
import kotlinx.android.synthetic.main.fragment_student_det_frekwencja.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class fragment_student_det_frekwencja : Fragment() {

    private lateinit var mViewModel: ViewModel_Main
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_det_frekwencja, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Recyclerview
        val adapter = AdapterSpotkania()
        val recyclerStudenci = view.recycler_frekwencja
        recyclerStudenci.adapter = adapter
        recyclerStudenci.layoutManager = LinearLayoutManager(requireContext())

        // ViewModel
        mViewModel = ViewModelProvider(this).get(ViewModel_Main::class.java)
        mViewModel.getSpotkania(sharedViewModel.curGroup.value!!).observe(viewLifecycleOwner, Observer {
                spotkania -> adapter.setData(spotkania, sharedViewModel.curStudent.value!!.studentId)
        })

    }
}