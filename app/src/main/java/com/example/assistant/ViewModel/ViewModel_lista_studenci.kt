package com.example.assistant.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.assistant.Model.Student
import com.example.assistant.Model.StudentDatabase
import com.example.assistant.Model.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class ViewModel_lista_studenci(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Student>>
    private val repository: StudentRepository

    init {
        val studentDAO = StudentDatabase.getDatabase(application).studentDAO()
        repository = StudentRepository(studentDAO)
        readAllData = repository.readAllData
    }

    fun addStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStudent(student)
        }
    }
}