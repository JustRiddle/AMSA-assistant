package com.example.assistant

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.assistant.Model.Grupa
import com.example.assistant.Model.Student
import com.example.assistant.ViewModel.ViewModel_grupy
import com.example.assistant.ViewModel.ViewModel_studenci
import kotlinx.android.synthetic.main.fragment_dodaj_grupe.*
import kotlinx.android.synthetic.main.fragment_dodaj_grupe.view.*
import kotlinx.android.synthetic.main.fragment_dodaj_studenta.*
import kotlinx.android.synthetic.main.fragment_dodaj_studenta.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class fragment_dodajGrupe : Fragment() {

    private lateinit var mGrupyViewModel: ViewModel_grupy

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_dodaj_grupe, container, false)

        mGrupyViewModel = ViewModelProvider(this).get(ViewModel_grupy::class.java)
        view.btn_dodajGrupe.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val nazwa = edit_nazwaGrupy.text.toString()
        val dzien = spinner_dni.selectedItem.toString()
        val godz_od = time_od.text.toString()
        val godz_do = time_do.text.toString()

        if (inputCheck(nazwa, godz_od, godz_do)) {

            // Tworzenie obiektu Student
            val grupa = Grupa(0, nazwa, dzien, godz_od, godz_do)

            // Dodanie do bazy
            mGrupyViewModel.addGroup(grupa)
            Toast.makeText(requireContext(), "Grupa dodana!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_fragment_dodajGrupe_to_fragment_tabs)

        } else {
            Toast.makeText(requireContext(), "Wypełnij wszystkie pola", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(nazwa: String, godz_od: String, godz_do: String): Boolean{
        return !(TextUtils.isEmpty(nazwa) && TextUtils.isEmpty(godz_do) && TextUtils.isEmpty(godz_od))
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinner = view.findViewById<Spinner>(R.id.spinner_dni)

        /** Obsługa spinnera */
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.dni_tygodnia,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }
}