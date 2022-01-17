package com.example.assistant.View.grupy

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
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
import com.example.assistant.ViewModel.AdapterNoweSpotkanie
import com.example.assistant.Model.Grupa
import com.example.assistant.Model.ObecnosciCallback
import com.example.assistant.Model.Spotkanie
import com.example.assistant.R
import com.example.assistant.ViewModel.ViewModel_Main
import kotlinx.android.synthetic.main.fragment_edytuj_spotkanie.*
import kotlinx.android.synthetic.main.fragment_edytuj_spotkanie.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class fragment_edytuj_spotkanie : Fragment(), ObecnosciCallback {

    private val args by navArgs<fragment_edytuj_spotkanieArgs>()
    private lateinit var mViewModel: ViewModel_Main
    private var obecnosci = mutableSetOf<Int>()
    private lateinit var currentGroup: Grupa

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("MOJE", args.currentSpotkanie.toString())
        val view = inflater.inflate(R.layout.fragment_edytuj_spotkanie, container, false)
        view.edit_dataSpotkania_zmiana.setText(args.currentSpotkanie.data)
        view.editText_temat_zmiana.setText(args.currentSpotkanie.temat)
        obecnosci = args.currentSpotkanie.obecnosc

        val adapter = AdapterNoweSpotkanie(this)
        val recyclerStudenci = view.recycler_obecnosci_zmiana

        recyclerStudenci.adapter = adapter
        recyclerStudenci.layoutManager = LinearLayoutManager(requireContext())

        // ViewModel
        mViewModel = ViewModelProvider(this).get(ViewModel_Main::class.java)
        mViewModel.getGrupabyID(args.currentSpotkanie.grupaID).observe(viewLifecycleOwner, Observer {
            grupa -> currentGroup = grupa[0]
            mViewModel.getStudenciWGrupie(currentGroup).observe(viewLifecycleOwner, Observer {
                    student -> adapter.setObecnosci(obecnosci)
                adapter.setData(student[0].studenci)
            })
        })


        view.btn_zmienSpotkanie.setOnClickListener {
            insertDataToDatabase()
        }

        view.btn_delete_spotkanie.setOnClickListener {
            mViewModel.deleteSpotkanie(args.currentSpotkanie)
            Toast.makeText(requireContext(), "Spotkanie Usunięte" +
                    "!", Toast.LENGTH_SHORT).show()
            val action = fragment_edytuj_spotkanieDirections.actionFragmentEdytujSpotkanieToFragmentGrupaDetails(currentGroup)
            view?.findNavController()?.navigate(action)
        }

        return view
    }


    private fun insertDataToDatabase() {
        val data = edit_dataSpotkania_zmiana.text.toString()
        val temat = editText_temat_zmiana.text.toString()

        if (inputCheck(data, temat)) {

            // Tworzenie obiektu Student
            val spotkanie = Spotkanie(args.currentSpotkanie.spotkanieId,args.currentSpotkanie.grupaID,data,temat,obecnosci)

            // Dodanie do bazy
            mViewModel.updateSpotkanie(spotkanie)
            Toast.makeText(requireContext(), "Spotkanie zmienione!", Toast.LENGTH_SHORT).show()
            val action = fragment_edytuj_spotkanieDirections.actionFragmentEdytujSpotkanieToFragmentGrupaDetails(currentGroup)
            view?.findNavController()?.navigate(action)

        } else {
            Toast.makeText(requireContext(), "Wypełnij wszystkie pola", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(data: String, temat: String): Boolean{
        return !(TextUtils.isEmpty(data) && TextUtils.isEmpty(temat))
    }

    override fun getList(lista: MutableSet<Int>) {
        obecnosci=lista
    }


}