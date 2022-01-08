package com.example.assistant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.assistant.Model.Grupa

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_dodajGrupe.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_dodajGrupe : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var grupy: MutableList<Grupa>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        grupy=(activity as MainActivity).grupy

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dodaj_grupe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nazwa = view.findViewById<TextView>(R.id.edit_nazwaGrupy)
        val spinner = view.findViewById<Spinner>(R.id.spinner_dni)
        val godz_od = view.findViewById<TextView>(R.id.time_od)
        val godz_do = view.findViewById<TextView>(R.id.time_do)

        /** Obsługa spinnera */
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.dni_tygodnia,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        /**  DODAWANIE DO LISTY      */
        view.findViewById<Button>(R.id.btn_dodajGrupe).setOnClickListener{
            grupy.add(
                Grupa(
                nazwa.text.toString(),
                spinner.selectedItem.toString(),
                godz_od.text.toString(),
                godz_do.text.toString()
            )
            )
            (activity as MainActivity).GrupyAdapter.notifyDataSetChanged()
            it.findNavController().navigate(R.id.action_fragment_dodajGrupe_to_fragment_tabs)


        }

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_dodajGrupe.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_dodajGrupe().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}