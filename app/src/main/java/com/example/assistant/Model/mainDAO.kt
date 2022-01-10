package com.example.assistant.Model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface mainDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGrupa(grupa: Grupa)

    @Query("SELECT * FROM student_table ORDER BY nazwisko ASC")
    fun getAllStudents(): LiveData<List<Student>>

    @Query("SELECT * FROM grupa_table ORDER BY id ASC")
    fun getAllGroups(): LiveData<List<Grupa>>

}