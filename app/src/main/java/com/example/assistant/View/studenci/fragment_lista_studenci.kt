package com.example.assistant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assistant.ViewModel.ViewModel_studenci
import kotlinx.android.synthetic.main.fragment_lista_studenci.view.*
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
class fragment_lista_studenci : Fragment() {

    private lateinit var mStudentViewModel: ViewModel_studenci

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lista_studenci, container, false)

        // Recyclerview
        val adapter = AdapterStudenci()
        val recyclerStudenci = view.recycler_studenci
        recyclerStudenci.adapter = adapter
        recyclerStudenci.layoutManager = LinearLayoutManager(requireContext())

        // ViewModel
        mStudentViewModel = ViewModelProvider(this).get(ViewModel_studenci::class.java)
        mStudentViewModel.getAllStudents.observe(viewLifecycleOwner, Observer {
            student -> adapter.setData(student)
        })

        view.btn_nowy_student.setOnClickListener{
            findNavController().navigate(R.id.action_fragment_tabs_to_dodajStudenta)
        }

        return view
    }

}
