package com.example.assistant.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "student_table")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val studentId: Int,
    val imie:String,
    val nazwisko:String,
    val nr_albumu:String
    ): Parcelable