package com.example.assistant.Model

import androidx.room.Entity

@Entity(primaryKeys = ["grupaId", "studentId"])
class GrupaStudentCross (
    val grupaId: Int,
    val studentId : Int
)