package com.example.assistant

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_student_details.*
import kotlinx.android.synthetic.main.fragment_student_details.view.*
import kotlinx.android.synthetic.main.fragment_tabs.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class fragment_student_details : Fragment() {

    private val args by navArgs<fragment_student_detailsArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_student_details, container, false)

        view.student_details_imieNazwisko.text = args.currentStudent.imie+" "+args.currentStudent.nazwisko
        view.student_details_nrAlbumu.text = args.currentStudent.nr_albumu

        view.btn_edytuj_studentDet.setOnClickListener {
            val action = fragment_student_detailsDirections.actionFragmentStudentDetailsToFragmentEdytujStudenta(args.currentStudent) //(studenci[position])
            view.findNavController().navigate(action)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager_studentDet.adapter = TabsAdapterStudentDet(childFragmentManager)
        tabStudentDet.setupWithViewPager(viewPager_studentDet)

    }

}