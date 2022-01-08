package com.example.assistant

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.assistant.Model.Grupa
import com.example.assistant.Model.Student

class MainActivity : AppCompatActivity(){

    val studenci = mutableListOf<Student>(
        Student("Adam","Abacki","123456"),
        Student("Bogdan", "Baacki", "789654"),
        Student("Celina", "Cabacka","741258")
    )
    val grupy = mutableListOf<Grupa>(
        Grupa("Remiza",   "Wtorek","16:00","17:30"),
        Grupa("Jutrzenka","Środa", "16:15","17:45"),
        Grupa("Wyszyński","Piątek","15:00","16:30")
    )

    val GrupyAdapter=AdapterGrupy(grupy)
    val StudenciAdapter=AdapterStudenci(studenci)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MOJE","Main OnCreate")

    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
//        Log.d("MOJE","Main OnCreateView")
        return super.onCreateView(name, context, attrs)
    }


}