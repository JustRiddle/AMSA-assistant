package com.example.assistant.Model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class StudenciWGrupie (
    @Embedded val grupa: Grupa,
    @Relation(
        parentColumn = "grupaId",
        entityColumn = "studentId",
        associateBy = Junction(GrupaStudentCross::class)
    )
    val studenci: List<Student>
)