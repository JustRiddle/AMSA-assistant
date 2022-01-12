package com.example.assistant.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.assistant.Model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class ViewModel_grupy(application: Application): AndroidViewModel(application) {


    val getAllGroups: LiveData<List<Grupa>>
    private val repository: mainRepository
    private val mainDAO :mainDAO = mainDatabase.getDatabase(application).mainDAO()

    init {
        repository = mainRepository(mainDAO)
        getAllGroups = repository.getAllGroups
    }

    fun addGroup(grupa: Grupa){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addGroup(grupa)
        }
    }

    fun updateGrupa(grupa: Grupa){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateGroup(grupa)
        }
    }

    fun getGrupyStudenta(grupa: Grupa):LiveData<List<StudenciWGrupie>>{
        Log.d("WEWNETRZNIE","BARDZO")
        val StudenciWGrupie:LiveData<List<StudenciWGrupie>> = mainDAO.getStudenciWGrupie(grupa.grupaId)
        return StudenciWGrupie
    }

}