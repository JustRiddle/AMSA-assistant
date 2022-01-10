package com.example.assistant

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_grupa_details.*
import kotlinx.android.synthetic.main.fragment_student_details.*
import kotlinx.coroutines.InternalCoroutinesApi

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_grupa_details.newInstance] factory method to
 * create an instance of this fragment.
 */
@InternalCoroutinesApi
class fragment_grupa_details : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grupa_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager_grupaDet.adapter = TabsAdapterGrupaDet(childFragmentManager)
        tabGrupaDet.setupWithViewPager(viewPager_grupaDet)

//        view.findViewById<Button>(R.id.btn_edytuj_grupaDet).setOnClickListener {
//            it.findNavController().navigate(R.id.action_fragment_grupa_details_to_fragment_dodajGrupe)
//        }
    }




}