package com.example.assistant.Model

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

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

    suspend fun updateGroup(grupa: Grupa){
        mainDAO.updateGrupa(grupa)
    }
    fun addEnrolment(grupa: Grupa, student: Student){
        mainDAO.addEnrolment(GrupaStudentCross(grupa.grupaId,student.studentId))
    }


    suspend fun addOcena(ocena: Ocena){
        mainDAO.addOcena(ocena)
    }

    suspend fun updateOcena(ocena: Ocena) {
        mainDAO.updateOcena(ocena)
    }


    suspend fun addSpotkanie(spotkanie: Spotkanie){
        mainDAO.addSpotkanie(spotkanie)
    }

    suspend fun updateSpotkanie(spotkanie: Spotkanie){
        mainDAO.updateSpotkanie(spotkanie)
    }


}