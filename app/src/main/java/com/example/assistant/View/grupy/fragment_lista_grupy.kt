package com.example.assistant

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assistant.ViewModel.ViewModel_grupy
import com.example.assistant.ViewModel.ViewModel_studenci
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_lista_grupy.*
import kotlinx.android.synthetic.main.fragment_lista_grupy.view.*
import kotlinx.android.synthetic.main.fragment_lista_studenci.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class fragment_lista_grupy : Fragment() {

    private lateinit var mGrupyViewModel: ViewModel_grupy

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_lista_grupy, container, false)

        //Recycler
        val adapter = AdapterGrupy()
        val recyclerGrupy = view.recycler_grupy
        recyclerGrupy.adapter = adapter
        recyclerGrupy.layoutManager = LinearLayoutManager(requireContext())

        // ViewModel
        mGrupyViewModel = ViewModelProvider(this).get(ViewModel_grupy::class.java)
        mGrupyViewModel.getAllGroups.observe(viewLifecycleOwner, Observer {
                grupa -> adapter.setData(grupa)
        })

        view.btn_nowa_grupa.setOnClickListener{
            findNavController().navigate(R.id.action_fragment_tabs_to_fragment_dodajGrupe)
        }

        return view
    }

}