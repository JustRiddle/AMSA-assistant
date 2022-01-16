package com.example.assistant

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.assistant.Model.Ocena
import com.example.assistant.ViewModel.SharedViewModel
import com.example.assistant.ViewModel.ViewModel_Main
import kotlinx.android.synthetic.main.fragment_edytuj_ocene.*
import kotlinx.android.synthetic.main.fragment_edytuj_ocene.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class fragment_edytuj_ocene : Fragment() {

    private val args by navArgs<fragment_edytuj_oceneArgs>()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var mStudentViewModel: ViewModel_Main

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_edytuj_ocene, container, false)

        mStudentViewModel = ViewModelProvider(this).get(ViewModel_Main::class.java)
        view.edit_ocena_zmiana.setText(args.currentOcena.ocena)
        view.editText_OcenaKomentarz_zmiana.setText(args.currentOcena.komentarz)
        view.text_ImieNazwisko_zmienOcene.text = sharedViewModel.curStudent.value?.imie+" "+sharedViewModel.curStudent.value?.nazwisko
        view.text_nazwaGr_zmienOcene.text = sharedViewModel.curGroup.value?.Nazwa


        view.btn_zmienOcene.setOnClickListener {
            updateItem()
        }

        return view
    }

    private fun updateItem() {
        val ocena = edit_ocena_zmiana.text.toString()
        val komentarz = editText_OcenaKomentarz_zmiana.text.toString()

        if (inputCheck(ocena, komentarz)) {
            // Tworzenie obiektu
            val ocenaDoWrzutu = Ocena(
                args.currentOcena.ocenaID,
                args.currentOcena.studentID,
                args.currentOcena.grupaID,
                ocena,
                komentarz
            )
            // Dodanie do bazy
            mStudentViewModel.updateOcena(ocenaDoWrzutu)
            Toast.makeText(requireContext(), "Ocena dodana!", Toast.LENGTH_SHORT).show()

            val action = fragment_edytuj_oceneDirections.actionFragmentEdytujOceneToFragmentStudentDetails(sharedViewModel.curStudent.value!!) //(studenci[position])
            this.findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Wypełnij wszystkie pola", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(imie: String, nazwisko: String): Boolean{
        return !(TextUtils.isEmpty(imie) && TextUtils.isEmpty(nazwisko))
    }

}