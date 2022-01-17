package com.example.assistant.ViewModel

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.assistant.Model.Grupa
import com.example.assistant.Model.SpinerGroupCallback
import kotlin.math.log

class AdapterSpinner(private val listener: SpinerGroupCallback): AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var items = parent?.getItemAtPosition(position).toString()
        val curentGroup = parent?.getItemAtPosition(position) as Grupa
        listener.onItemSelect(curentGroup)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

        Toast.makeText(parent?.context, "Nic nie wybrano", Toast.LENGTH_SHORT).show()
    }
}