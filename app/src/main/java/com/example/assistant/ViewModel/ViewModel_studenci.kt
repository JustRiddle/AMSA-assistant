package com.example.assistant.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.assistant.Model.Student
import com.example.assistant.Model.mainDatabase
import com.example.assistant.Model.mainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class ViewModel_studenci(application: Application): AndroidViewModel(application) {

    val getAllStudents: LiveData<List<Student>>
    private val repository: mainRepository

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
}