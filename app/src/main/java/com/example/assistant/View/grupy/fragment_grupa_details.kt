package com.example.assistant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.assistant.ViewModel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_grupa_details.*
import kotlinx.android.synthetic.main.fragment_grupa_details.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class fragment_grupa_details() : Fragment(){

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val args by navArgs<fragment_grupa_detailsArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedViewModel.setCurrentGroup(args.currentGroup)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_grupa_details, container, false)
        val child = inflater.inflate(R.layout.fragment_grupa_det_studenci, container, false)


        view.text_nazwaGr_detail.text = args.currentGroup.Nazwa
        view.text_kiedy_detail.text = args.currentGroup.dzien_tygodnia+" "+args.currentGroup.godz_od+"-"+args.currentGroup.godz_do

        view.btn_edytuj_grupaDet.setOnClickListener {
            val action = fragment_grupa_detailsDirections.actionFragmentGrupaDetailsToFragmentEdytujGrupe(args.currentGroup) //(studenci[position])

            view.findNavController().navigate(action)
        }





        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager_grupaDet.adapter = TabsAdapterGrupaDet(childFragmentManager)
        tabGrupaDet.setupWithViewPager(viewPager_grupaDet)
        //curGrupa.setCurGrupa(args.currentGroup)

//        val parent: fragment_grupa_det_studenci =
//            childFragmentManager.findFragmentById(R.id.fragment_grupa_det_studenci) as fragment_grupa_det_studenci
//        parent.setCurrGroup(args.currentGroup)
//        Log.d("MOJE", "Przesłano")


    }
}