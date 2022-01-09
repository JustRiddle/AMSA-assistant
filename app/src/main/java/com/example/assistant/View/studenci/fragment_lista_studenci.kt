package com.example.assistant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assistant.ViewModel.ViewModel_lista_studenci
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_lista_studenci.view.*
import kotlinx.coroutines.InternalCoroutinesApi

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_lista_studenci.newInstance] factory method to
 * create an instance of this fragment.
 */
@InternalCoroutinesApi
class fragment_lista_studenci : Fragment() {

    private lateinit var mStudentViewModel: ViewModel_lista_studenci

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
        mStudentViewModel = ViewModelProvider(this).get(ViewModel_lista_studenci::class.java)
        mStudentViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            student -> adapter.setData(student)
        })

        view.btn_nowy_student.setOnClickListener{
            findNavController().navigate(R.id.action_fragment_tabs_to_dodajStudenta)
        }

        return view
    }

}
