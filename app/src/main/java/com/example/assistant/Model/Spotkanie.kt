package com.example.assistant.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Spotkanie (
    @PrimaryKey(autoGenerate = true)
    val spotkanieId: Int,
    val grupaID:Int,
    val data:String,
    val temat:String,
    val obecnosc:MutableSet<Int>
):Parcelable

class ObecnoscConverter {
    @TypeConverter
    fun fromString(value: String?): MutableSet<Int> {
        val listType = object : TypeToken<MutableSet<Int>>() {}.type
        return Gson().fromJson(value, listType)

    }

    @TypeConverter
    fun fromSet(value: MutableSet<Int>): String {
        return Gson().toJson(value)
    }
}