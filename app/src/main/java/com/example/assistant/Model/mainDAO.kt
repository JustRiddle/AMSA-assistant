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

    @Update
    suspend fun updateGrupa(grupa: Grupa)

    @Query("SELECT * FROM grupa_table ORDER BY grupaId ASC")
    fun getAllGroups(): LiveData<List<Grupa>>

    /** STUDENCI x GRUPY */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addEnrolment(crossRef: GrupaStudentCross)

    @Query("SELECT * FROM grupa_table WHERE grupaId = :grupaId ORDER BY grupaId ASC")
    fun getStudenciWGrupie(grupaId: Int): LiveData<List<StudenciWGrupie>>

    @Query("SELECT * FROM student_table WHERE studentId = :studentId ORDER BY nazwisko ASC")
    fun getGrupyStudenta(studentId: Int): LiveData<List<GrupyStudenta>>

    @Query("SELECT * FROM GrupaStudentCross ORDER BY grupaId ASC")
    fun getAllEnrolments(): LiveData<List<GrupaStudentCross>>


}