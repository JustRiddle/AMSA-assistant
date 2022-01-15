package com.example.assistant.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "grupa_table")
data class Grupa(
    @PrimaryKey(autoGenerate = true)
    val grupaId: Int,
    val Nazwa:String,
    val dzien_tygodnia:String,
    val godz_od:String,
    val godz_do:String
    ): Parcelable{
        override fun toString(): String { return Nazwa }
    }