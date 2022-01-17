package com.example.assistant.View.grupy

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.assistant.Model.Grupa
import com.example.assistant.R
import com.example.assistant.ViewModel.ViewModel_Main
import kotlinx.android.synthetic.main.fragment_edytuj_grupe.*
import kotlinx.android.synthetic.main.fragment_edytuj_grupe.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class fragment_edytuj_grupe : Fragment() {

    private val args by navArgs<fragment_edytuj_grupeArgs>()
    private lateinit var mViewModel: ViewModel_Main

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edytuj_grupe, container, false)

        mViewModel = ViewModelProvider(this).get(ViewModel_Main::class.java)
        view.edit_nazwaGrupy_zmiana.setText(args.currentGroup.Nazwa)

        view.time_od_zmiana.setText(args.currentGroup.godz_od)
        view.time_do_zmiana.setText(args.currentGroup.godz_do)

        view.btn_edytujGrupe.setOnClickListener {
            if(updateItem()){
                val action = fragment_edytuj_grupeDirections.actionFragmentEdytujGrupeToFragmentGrupaDetails(args.currentGroup) //(studenci[position])
                view.findNavController().navigate(action)
            }
        }

        view.btn_delete_group.setOnClickListener {

            mViewModel.deleteGrupa(args.currentGroup)
            Toast.makeText(requireContext(), "Grupa usunięta!", Toast.LENGTH_SHORT).show()
            val action = fragment_edytuj_grupeDirections.actionFragmentEdytujGrupeToFragmentTabs()
            view.findNavController().navigate(action)
        }


        return view
    }

    private fun updateItem(): Boolean {
        val nazwa = edit_nazwaGrupy_zmiana.text.toString()
        val dzien = spinner_dni_zmiana.selectedItem.toString()
        val godz_od = time_od_zmiana.text.toString()
        val godz_do = time_do_zmiana.text.toString()

        if (inputCheck(nazwa, godz_od, godz_do)) {

            // Tworzenie obiektu Student
            val grupa = Grupa(args.currentGroup.grupaId, nazwa, dzien, godz_od, godz_do)

            // Dodanie do bazy
            mViewModel.updateGrupa(grupa)
            Toast.makeText(requireContext(), "Grupa zaktualizowana!", Toast.LENGTH_SHORT).show()
            return true

        } else {
            Toast.makeText(requireContext(), "Wypełnij wszystkie pola", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    private fun inputCheck(nazwa: String, godz_od: String, godz_do: String): Boolean{
        return !(TextUtils.isEmpty(nazwa) && TextUtils.isEmpty(godz_do) && TextUtils.isEmpty(godz_od))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinner = view.findViewById<Spinner>(R.id.spinner_dni_zmiana)

        /** Obsługa spinnera */
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.dni_tygodnia,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.setSelection(
            when(args.currentGroup.dzien_tygodnia){
                "Poniedziałek"  ->0
                "Wtorek"        -> 1
                "Środa"         -> 2
                "Czwartek"      -> 3
                "Piątek"        -> 4
                "Sobota"        -> 5
                "Niedziela"     -> 6
                else ->0
            }
        )
    }

}