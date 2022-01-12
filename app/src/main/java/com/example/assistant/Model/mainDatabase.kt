package com.example.assistant.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [
        Student::class,
        Grupa::class,
        GrupaStudentCross::class
    ],
    version = 1,
    exportSchema = false)
abstract class mainDatabase: RoomDatabase() {

    abstract fun mainDAO(): mainDAO

    companion object{
        @Volatile
        private var INSTANCE: mainDatabase? = null

        @InternalCoroutinesApi
        fun getDatabase(context: Context): mainDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    mainDatabase::class.java,
                    "T2"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}