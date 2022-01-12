package com.example.assistant.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assistant.Model.Grupa

class SharedViewModel: ViewModel() {
    var curGroup = MutableLiveData(Grupa(0,"","","",""))
    val currentGroup: LiveData<Grupa> = curGroup




    fun setCurrentGroup(grupa: Grupa)
    {
        curGroup.value = grupa
    }
}