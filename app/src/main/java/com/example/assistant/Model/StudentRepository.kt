package com.example.assistant.Model

import androidx.lifecycle.LiveData

class StudentRepository(private  val studentDAO: StudentDAO) {
    val readAllData: LiveData<List<Student>> = studentDAO.readAllData()

    suspend fun addStudent(student: Student){
        studentDAO.addStudent(student)
    }
}