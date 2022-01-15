package com.example.assistant.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assistant.Model.Grupa
import com.example.assistant.Model.Student

class SharedViewModel: ViewModel() {
    var curGroup = MutableLiveData(Grupa(0,"","","",""))
    var curStudent = MutableLiveData(Student(0,"","",""))


    fun setCurrentGroup(grupa: Grupa)
    {
        curGroup.value = grupa
    }

    fun setCurrentStudent(student: Student)
    {
        curStudent.value = student
    }



}