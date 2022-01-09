package com.example.assistant

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabsAdapterGrupaDet(fm:FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when(position){
            0 ->{return fragment_grupa_det_spotkania()}
            1 ->{return fragment_grupa_det_studenci()}

            else ->{return  fragment_lista_grupy()}
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position)
        {
            0 -> {return "Spotkania"}
            1 -> {return "Studenci"}
        }
        return super.getPageTitle(position)
    }

    override fun getCount(): Int {
        return 2
    }
}