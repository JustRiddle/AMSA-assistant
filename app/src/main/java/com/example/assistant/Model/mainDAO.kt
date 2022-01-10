package com.example.assistant.Model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface mainDAO {

    /**  STUDENCI */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Query("SELECT * FROM student_table ORDER BY nazwisko ASC")
    fun getAllStudents(): LiveData<List<Student>>


    /**  GRUPY  */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGrupa(grupa: Grupa)

    @Query("SELECT * FROM grupa_table ORDER BY id ASC")
    fun getAllGroups(): LiveData<List<Grupa>>

}