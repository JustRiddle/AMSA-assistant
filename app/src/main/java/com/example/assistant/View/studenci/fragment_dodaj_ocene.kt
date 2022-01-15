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
import androidx.navigation.fragment.navArgs
import com.example.assistant.Model.Ocena
import com.example.assistant.Model.Student
import com.example.assistant.ViewModel.ViewModel_studenci
import kotlinx.android.synthetic.main.fragment_dodaj_ocene.*
import kotlinx.android.synthetic.main.fragment_dodaj_ocene.view.*
import kotlinx.android.synthetic.main.fragment_dodaj_studenta.*
import kotlinx.android.synthetic.main.fragment_dodaj_studenta.view.*
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
class fragment_dodaj_ocene : Fragment() {

    private val args by navArgs<fragment_dodaj_oceneArgs>()
    private lateinit var mStudentViewModel: ViewModel_studenci


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dodaj_ocene, container, false)
        mStudentViewModel = ViewModelProvider(this).get(ViewModel_studenci::class.java)

        view.btn_dodajOcene.setOnClickListener {
            insertDataToDatabase()
        }

        view.text_nazwaGr_dodajOcene.text = args.currentGroup.Nazwa
        view.text_ImieNazwisko_dodajOcene.text = args.currentStudent.imie+" "+args.currentStudent.nazwisko

        return view
    }

    private fun insertDataToDatabase() {
        val ocena = edit_ocena.text.toString()
        val komentarz = editText_OcenaKomentarz.text.toString()

        if (inputCheck(ocena, komentarz)) {
            // Tworzenie obiektu
            val ocenaDoWrzutu = Ocena(
                0,
                args.currentStudent.studentId,
                args.currentGroup.grupaId,
                ocena,
                komentarz
            )
            // Dodanie do bazy
            mStudentViewModel.addOcena(ocenaDoWrzutu)
            Toast.makeText(requireContext(), "Ocena dodana!", Toast.LENGTH_SHORT).show()

            val action = fragment_dodaj_oceneDirections.actionFragmentDodajOceneToFragmentStudentDetails(args.currentStudent) //(studenci[position])
            this.findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Wypełnij wszystkie pola", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(imie: String, nazwisko: String): Boolean{
        return !(TextUtils.isEmpty(imie) && TextUtils.isEmpty(nazwisko))
    }


}