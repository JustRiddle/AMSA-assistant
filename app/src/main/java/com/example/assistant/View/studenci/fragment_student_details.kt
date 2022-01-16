package com.example.assistant

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.assistant.Model.Grupa
import com.example.assistant.Model.SpinerGroupCallback
import com.example.assistant.ViewModel.AdapterSpinner
import com.example.assistant.ViewModel.SharedViewModel
import com.example.assistant.ViewModel.ViewModel_Main
import kotlinx.android.synthetic.main.fragment_student_details.*
import kotlinx.android.synthetic.main.fragment_student_details.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class fragment_student_details : Fragment(), SpinerGroupCallback {

    private val args by navArgs<fragment_student_detailsArgs>()
    private lateinit var mViewModel: ViewModel_Main

    private lateinit var arrayAdapter: ArrayAdapter<Grupa>
    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedViewModel.setCurrentStudent(args.currentStudent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_student_details, container, false)
        mViewModel = ViewModelProvider(this).get(ViewModel_Main::class.java)


        /** Obsługa spinnera */

        val spinner = view.findViewById<Spinner>(R.id.spinner_wybierzGrupe)
        arrayAdapter= ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = AdapterSpinner(this)
        mViewModel.getGrupyStudenta(args.currentStudent).observe(viewLifecycleOwner, Observer {

                grupy -> arrayAdapter.addAll(grupy[0].grupy)
        })


        Log.d("MOJE", "TO MA BY PRZED VMem")



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

//        viewPager_studentDet.adapter = TabsAdapterStudentDet(childFragmentManager)
//        tabStudentDet.setupWithViewPager(viewPager_studentDet)



    }

    override fun onItemSelect(grupa: Grupa) {
        Log.d("MOJE", "AKTUALIZACJA SHARED GRUPY!")
        sharedViewModel.setCurrentGroup(grupa)
        viewPager_studentDet.adapter = TabsAdapterStudentDet(childFragmentManager)
        tabStudentDet.setupWithViewPager(viewPager_studentDet)
    }


}