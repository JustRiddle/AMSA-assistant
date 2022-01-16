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
import com.example.assistant.ViewModel.ViewModel_Main
import kotlinx.android.synthetic.main.fragment_lista_grupy.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class fragment_lista_grupy : Fragment() {

    private lateinit var mViewModel: ViewModel_Main

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
        mViewModel = ViewModelProvider(this).get(ViewModel_Main::class.java)
        mViewModel.getAllGroups.observe(viewLifecycleOwner, Observer {
                grupa -> adapter.setData(grupa)
        })

        view.btn_nowa_grupa.setOnClickListener{
            findNavController().navigate(R.id.action_fragment_tabs_to_fragment_dodajGrupe)
        }

        return view
    }

}