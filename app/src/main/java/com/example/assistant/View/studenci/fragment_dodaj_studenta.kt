package com.example.assistant

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.assistant.Model.Student
import com.example.assistant.ViewModel.ViewModel_studenci
import kotlinx.android.synthetic.main.fragment_dodaj_studenta.*
import kotlinx.android.synthetic.main.fragment_dodaj_studenta.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class DodajStudenta : Fragment() {


    private lateinit var mStudentViewModel: ViewModel_studenci


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dodaj_studenta, container, false)

        mStudentViewModel = ViewModelProvider(this).get(ViewModel_studenci::class.java)
        view.btn_dodajStudenta.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val imie = edit_imie.text.toString()
        val nazwisko = edit_nazwisko.text.toString()
        val nr_albumu = edit_nr_albumu.text.toString()

        if (inputCheck(imie, nazwisko, nr_albumu)) {
            // Tworzenie obiektu Student
            val student = Student(0, imie, nazwisko, nr_albumu)
            // Dodanie do bazy
            mStudentViewModel.addStudent(student)
            Toast.makeText(requireContext(), "Student dodany!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_dodajStudenta_to_fragment_tabs)
        } else {
            Toast.makeText(requireContext(), "Wypełnij wszystkie pola", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(imie: String, nazwisko: String, nr_albumu: String): Boolean{
        return !(TextUtils.isEmpty(imie) && TextUtils.isEmpty(nazwisko) && TextUtils.isEmpty(nr_albumu))
    }


}