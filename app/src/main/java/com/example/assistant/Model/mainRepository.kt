package com.example.assistant.Model

import androidx.lifecycle.LiveData

class mainRepository(private  val mainDAO: mainDAO) {
    val getAllStudents: LiveData<List<Student>> = mainDAO.getAllStudents()
    val getAllGroups: LiveData<List<Grupa>> = mainDAO.getAllGroups()

    suspend fun addStudent(student: Student){
        mainDAO.addStudent(student)
    }

    suspend fun updateStudent(student: Student){
        mainDAO.updateStudent(student)
    }

    suspend fun addGroup(grupa: Grupa){
        mainDAO.addGrupa(grupa)
    }
}