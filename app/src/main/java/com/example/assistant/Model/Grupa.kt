package com.example.assistant.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grupa_table")
class Grupa(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val Nazwa:String,
    val dzien_tygodnia:String,
    val godz_od:String,
    val godz_do:String
    )