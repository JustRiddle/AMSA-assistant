package com.example.assistant

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabsAdapterStudentDet(fm:FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when(position){
            0 ->{return fragment_student_det_frekwencja()}
            1 ->{return fragment_student_det_oceny()}

            else ->{return  fragment_lista_grupy()}
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position)
        {
            0 -> {return "Frekwencja"}
            1 -> {return  "Oceny"}
        }
        return super.getPageTitle(position)
    }

    override fun getCount(): Int {
        return 2
    }
}