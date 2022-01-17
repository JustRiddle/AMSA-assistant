package com.example.assistant

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assistant.Model.*
import com.example.assistant.ViewModel.AdapterNoweSpotkanie
import com.example.assistant.ViewModel.ViewModel_Main
import kotlinx.android.synthetic.main.fragment_dodaj_spotkanie.*
import kotlinx.android.synthetic.main.fragment_dodaj_spotkanie.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class fragment_dodaj_spotkanie : Fragment(), ObecnosciCallback {

    private val args by navArgs<fragment_dodaj_spotkanieArgs>()
    private lateinit var mStudentViewModel: ViewModel_Main
    private var obecnosci = mutableSetOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dodaj_spotkanie, container, false)
//
        val adapter = AdapterNoweSpotkanie(this)
        val recyclerStudenci = view.recycler_obecnosci
        recyclerStudenci.adapter = adapter
        recyclerStudenci.layoutManager = LinearLayoutManager(requireContext())

        // ViewModel
        mStudentViewModel = ViewModelProvider(this).get(ViewModel_Main::class.java)
        mStudentViewModel.getStudenciWGrupie(args.currentGroup).observe(viewLifecycleOwner, Observer {
                student -> adapter.setData(student[0].studenci)
        })

        view.btn_dodajSpotkanie.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }


    private fun insertDataToDatabase() {
        val data = edit_dataSpotkania.text.toString()
        val temat = editText_temat.text.toString()

        if (inputCheck(data, temat)) {

            // Tworzenie obiektu Student
            val spotkanie = Spotkanie(0,args.currentGroup.grupaId,data,temat,obecnosci)

            // Dodanie do bazy
            mStudentViewModel.addSpotkanie(spotkanie)
            Toast.makeText(requireContext(), "Spotkanie zapisane!", Toast.LENGTH_SHORT).show()
            val action = fragment_dodaj_spotkanieDirections.actionFragmentDodajSpotkanieToFragmentGrupaDetails(args.currentGroup) //(studenci[position])
            view?.findNavController()?.navigate(action)

        } else {
            Toast.makeText(requireContext(), "Wypełnij wszystkie pola", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(data: String, temat: String): Boolean{
        return !(TextUtils.isEmpty(data) && TextUtils.isEmpty(temat))
    }

    override fun getList(lista: MutableSet<Int>) {
        obecnosci = lista
    }


}