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
class ViewModel_Main(application: Application): AndroidViewModel(application) {

    val getAllStudents: LiveData<List<Student>>
    val getAllGroups: LiveData<List<Grupa>>
    private val repository: mainRepository
    private val mainDAO :mainDAO = mainDatabase.getDatabase(application).mainDAO()


    init {
        repository = mainRepository(mainDAO)
        getAllStudents = repository.getAllStudents
        getAllGroups = repository.getAllGroups
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

    fun getGrupabyID(groupID: Int):LiveData<List<Grupa>>{
        val Grupa:LiveData<List<Grupa>> = mainDAO.getAllGroups()
        return Grupa
    }

    fun getGrupyStudenta(student: Student):LiveData<List<GrupyStudenta>>{
        val GrupyStudenta:LiveData<List<GrupyStudenta>> = mainDAO.getGrupyStudenta(student.studentId)
        return GrupyStudenta
    }





    fun addEnrolment(grupa: Grupa, student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEnrolment(grupa, student)
        }
    }

    fun addOcena(ocena: Ocena){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addOcena(ocena)
        }
    }

    fun updateOcena(ocena: Ocena){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateOcena(ocena)
        }
    }



    fun getStudenciWGrupie(grupa: Grupa):LiveData<List<StudenciWGrupie>>{
        val StudenciWGrupie:LiveData<List<StudenciWGrupie>> = mainDAO.getStudenciWGrupie(grupa.grupaId)
        return StudenciWGrupie
    }

    fun getOceny(grupa: Grupa, student: Student):LiveData<List<Ocena>>{
        val ocenki:LiveData<List<Ocena>> = mainDAO.getOceny(student.studentId, grupa.grupaId)
        return ocenki
    }

    fun addSpotkanie(spotkanie: Spotkanie){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSpotkanie(spotkanie)
        }
    }

    fun updateSpotkanie(spotkanie: Spotkanie){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateSpotkanie(spotkanie)
        }
    }

    fun getSpotkania(grupa: Grupa):LiveData<List<Spotkanie>>{
        val spotkania:LiveData<List<Spotkanie>> = mainDAO.getSpotkania(grupa.grupaId)
        return spotkania
    }

}