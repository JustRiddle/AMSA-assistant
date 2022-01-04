package com.example.assistant

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_lista_grupy.*

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = PageAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
        Log.d("MOJE","MainActivity OnCreate")

    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        Log.d("MOJE","Main OnCreateView")
        return super.onCreateView(name, context, attrs)
    }


}