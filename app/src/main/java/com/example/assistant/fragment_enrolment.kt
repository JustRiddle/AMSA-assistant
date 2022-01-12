package com.example.assistant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assistant.Model.RecyclerCallback
import com.example.assistant.Model.Student
import com.example.assistant.ViewModel.ViewModel_studenci
import kotlinx.android.synthetic.main.fragment_enrolment.view.*
import kotlinx.android.synthetic.main.fragment_lista_studenci.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class fragment_enrolment : Fragment(), RecyclerCallback {


    private val args by navArgs<fragment_enrolmentArgs>()
    private lateinit var mStudentViewModel: ViewModel_studenci

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_enrolment, container, false)

        val adapter = AdapterEnrolment(this)
        val recyclerStudenci = view.recycler_enrolment
        recyclerStudenci.adapter = adapter
        recyclerStudenci.layoutManager = LinearLayoutManager(requireContext())

        // ViewModel
        mStudentViewModel = ViewModelProvider(this).get(ViewModel_studenci::class.java)
        mStudentViewModel.getAllStudents.observe(viewLifecycleOwner, Observer {
                student -> adapter.setData(student,args.currentGroup )
        })

        view.enrolments_nazwaGr.text = args.currentGroup.Nazwa


        return view
    }

    override fun onItemClick(student: Student) {
        mStudentViewModel.addEnrolment(args.currentGroup, student)
        Toast.makeText(context,"Zapisano "+student.nazwisko+ " do grupy "+args.currentGroup.Nazwa,Toast.LENGTH_SHORT).show()


    }


}