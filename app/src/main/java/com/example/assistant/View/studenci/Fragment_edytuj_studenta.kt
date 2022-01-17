package com.example.assistant.View.studenci

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.assistant.Model.Student
import com.example.assistant.R
import com.example.assistant.ViewModel.ViewModel_Main
import kotlinx.android.synthetic.main.fragment_edytuj_studenta.*
import kotlinx.android.synthetic.main.fragment_edytuj_studenta.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class fragment_edytuj_studenta : Fragment() {

    private val args by navArgs<fragment_edytuj_studentaArgs>()
    private lateinit var mStudentViewModel: ViewModel_Main

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edytuj_studenta, container, false)

        mStudentViewModel = ViewModelProvider(this).get(ViewModel_Main::class.java)
        view.edit_imie_zmiana.setText(args.currentStudent.imie)
        view.edit_nazwisko_zmiana.setText(args.currentStudent.nazwisko)
        view.edit_nr_albumu_zmiana.setText(args.currentStudent.nr_albumu)

        view.btn_edytujStudenta.setOnClickListener {
            if(updateItem()){
                val action = fragment_edytuj_studentaDirections.actionFragmentEdytujStudentaToFragmentStudentDetails(args.currentStudent) //(studenci[position])
                view.findNavController().navigate(action)
            }
        }

        view.btn_delete_student.setOnClickListener {
            mStudentViewModel.deleteStudent(args.currentStudent)
            Toast.makeText(requireContext(), "Student usunięty!", Toast.LENGTH_SHORT).show()
            val action = fragment_edytuj_studentaDirections.actionFragmentEdytujStudentaToFragmentTabs()
            view.findNavController().navigate(action)
        }
        return view
    }

    private fun updateItem(): Boolean {
        val imie = edit_imie_zmiana.text.toString()
        val nazwisko = edit_nazwisko_zmiana.text.toString()
        val nr_albumu = edit_nr_albumu_zmiana.text.toString()

        if(inputCheck(imie,nazwisko,nr_albumu))
        {
            val updatedStudent = Student(args.currentStudent.studentId, imie, nazwisko, nr_albumu)
            mStudentViewModel.updateStudent(updatedStudent)

            Toast.makeText(requireContext(), "Dane o studencie zaktualizowane!", Toast.LENGTH_SHORT).show()

            return true

        } else {
            Toast.makeText(requireContext(), "Uzupełnij wszystkie pola!", Toast.LENGTH_SHORT).show()
            return false
        }

    }

    private fun inputCheck(imie: String, nazwisko: String, nr_albumu: String): Boolean{
        return !(TextUtils.isEmpty(imie) && TextUtils.isEmpty(nazwisko) && TextUtils.isEmpty(nr_albumu))
    }

}