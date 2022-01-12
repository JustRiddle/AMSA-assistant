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
    private val mainDAO :mainDAO = mainDatabase.getDatabase(application).mainDAO()


    init {
        repository = mainRepository(mainDAO)
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



    fun getStudenciWGrupie(grupa: Grupa):LiveData<List<StudenciWGrupie>>{
        Log.d("WEWNETRZNIE","BARDZO")
        val StudenciWGrupie:LiveData<List<StudenciWGrupie>> = mainDAO.getStudenciWGrupie(grupa.grupaId)
        return StudenciWGrupie
    }

}