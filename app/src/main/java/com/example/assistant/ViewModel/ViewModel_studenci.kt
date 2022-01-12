package com.example.assistant.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.assistant.Model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class ViewModel_studenci(application: Application): AndroidViewModel(application) {

    val getAllStudents: LiveData<List<Student>>
    private val repository: mainRepository
    val returnedVal = MutableLiveData<List<StudenciWGrupie>>()
    init {
        val studentDAO = mainDatabase.getDatabase(application).mainDAO()
        repository = mainRepository(studentDAO)
        getAllStudents = repository.getAllStudents
    }

    fun addStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStudent(student)
        }
    }

    fun updateStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateStudent(student)
        }
    }

    fun addEnrolment(grupa: Grupa, student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEnrolment(grupa, student)
        }
    }

//    private fun _getGroupStudents(grupa: Grupa)
//    {
//        lateinit var result:LiveData<List<StudenciWGrupie>>
//        viewModelScope.launch(Dispatchers.IO) {
//            returnedGetGroup = repository.getStudenciWGrupie(grupa)
//        }
//    }

    fun getGroupStudents(grupa: Grupa){
        Log.d("WEWNETRZNIE","BARDZO")
        viewModelScope.launch {
            returnedVal.value = repository.getStudenciWGrupie(grupa).value
        }
    }

}