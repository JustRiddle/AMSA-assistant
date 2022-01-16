package com.example.assistant

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.assistant.Model.Grupa
import com.example.assistant.Model.Ocena
import com.example.assistant.Model.Spotkanie
import com.example.assistant.Model.Student

class MainActivity : AppCompatActivity(){

//    val spotkania = mutableListOf<Spotkanie>(
//        Spotkanie(1,"11.01.2021", "Pojazd czterokołowy ze skrętną przeednią osią"),
//        Spotkanie(1,"18.01.2021", "Walki robotów- konstrukcje własne"),
//        Spotkanie(1,"25.01.2021", "Gitary")
//    )
//    val SpotkaniaAdapter=AdapterSpotkania(spotkania)
    ////////////////   ^   Do odstrzału   ^   ////////////////




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.MainfragmentContainer))

    }
}