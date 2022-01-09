package com.example.assistant.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val imie:String,
    val nazwisko:String,
    val nr_albumu:String
    )