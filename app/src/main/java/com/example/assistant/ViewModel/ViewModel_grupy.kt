package com.example.assistant.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.assistant.Model.Grupa
import com.example.assistant.Model.Student
import com.example.assistant.Model.mainDatabase
import com.example.assistant.Model.mainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class ViewModel_grupy(application: Application): AndroidViewModel(application) {

    val getAllGroups: LiveData<List<Grupa>>
    private val repository: mainRepository

    init {
        val studentDAO = mainDatabase.getDatabase(application).mainDAO()
        repository = mainRepository(studentDAO)
        getAllGroups = repository.getAllGroups
    }

    fun addGroup(grupa: Grupa){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addGroup(grupa)
        }
    }
}