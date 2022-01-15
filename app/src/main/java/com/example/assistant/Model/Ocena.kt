package com.example.assistant.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "ocena_table")
data class Ocena(
    @PrimaryKey(autoGenerate = true)
    val ocenaID: Int,
    val studentID: Int,
    val grupaID: Int,
    val ocena:String,
    val komentarz:String
    ):Parcelable