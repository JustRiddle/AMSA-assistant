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

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("SELECT * FROM student_table ORDER BY nazwisko ASC")
    fun getAllStudents(): LiveData<List<Student>>


    /**  GRUPY  */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGrupa(grupa: Grupa)

    @Update
    suspend fun updateGrupa(grupa: Grupa)

    @Delete
    suspend fun deleteGrupa(grupa: Grupa)

    @Query("SELECT * FROM grupa_table ORDER BY grupaId ASC")
    fun getAllGroups(): LiveData<List<Grupa>>

    @Query("SELECT * FROM grupa_table ORDER BY grupaId ASC")
    fun getGroup(): LiveData<List<Grupa>>

    /** STUDENCI x GRUPY */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addEnrolment(crossRef: GrupaStudentCross)

    @Query("SELECT * FROM grupa_table WHERE grupaId = :grupaId ORDER BY grupaId ASC")
    fun getStudenciWGrupie(grupaId: Int): LiveData<List<StudenciWGrupie>>

    @Transaction
    @Query("SELECT * FROM student_table WHERE studentId = :studentId ORDER BY nazwisko ASC")
    fun getGrupyStudenta(studentId: Int): LiveData<List<GrupyStudenta>>

    @Transaction
    @Query("SELECT * FROM GrupaStudentCross ORDER BY grupaId ASC")
    fun getAllEnrolments(): LiveData<List<GrupaStudentCross>>

    @Query("DELETE FROM grupastudentcross WHERE grupaId = :groupID")
    suspend fun deleteEnrolmentByGroup(groupID: Int)

    @Query("DELETE FROM grupastudentcross WHERE studentId = :studentID")
    suspend fun deleteEnrolmentByStudent(studentID: Int)

    /** OCENY */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addOcena(ocena: Ocena)

    @Update
    suspend fun updateOcena(ocena: Ocena)

    @Delete
    suspend fun deleteOcena(ocena: Ocena)

    @Query("SELECT * FROM ocena_table WHERE studentID = :studentID AND grupaID = :grupaID ORDER BY ocenaID ASC")
    fun getOceny(studentID: Int, grupaID: Int): LiveData<List<Ocena>>

    @Query("SELECT * FROM ocena_table ORDER BY ocenaID ASC")
    fun getAllOceny(): LiveData<List<Ocena>>

    /** SPOTKANIA */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSpotkanie(spotkanie: Spotkanie)

    @Update
    suspend fun updateSpotkanie(spotkanie: Spotkanie)

    @Delete
    suspend fun deleteSpotkanie(spotkanie: Spotkanie)

    @Query("SELECT * FROM spotkanie WHERE grupaID = :grupaID ORDER BY spotkanieId ASC")
    fun getSpotkania(grupaID: Int): LiveData<List<Spotkanie>>

}