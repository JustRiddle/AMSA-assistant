package com.example.assistant.Model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class GrupyStudenta (
    @Embedded val student: Student,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "grupaId",
        associateBy = Junction(GrupaStudentCross::class)
    )
    val grupy: List<Grupa>
)