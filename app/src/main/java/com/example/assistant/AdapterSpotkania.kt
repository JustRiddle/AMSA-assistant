package com.example.assistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.assistant.Model.Spotkanie
import com.example.assistant.Model.Student

class AdapterSpotkania(val spotkania: List<Spotkanie>):RecyclerView.Adapter<AdapterSpotkania.Holder>() {
    lateinit var obecnosc: String
    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewData: TextView
        val textViewTemat: TextView
        val textViewObecnosc: TextView


        init {
            textViewData = itemView.findViewById<TextView>(R.id.textView_Data)
            textViewTemat = itemView.findViewById<TextView>(R.id.textView_Temat)
            textViewObecnosc = itemView.findViewById<TextView>(R.id.textView_obecnosc)

            itemView.setOnClickListener {
                // TODO Wrzucić tutaj nav do widoku obecności
            //  it.findNavController().navigate(R.id.action_fragment_tabs_to_fragment_student_details)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_item_spotkania, parent,false) as View

        return Holder(view)

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.textViewData.text=spotkania[position].data
        holder.textViewTemat.text=spotkania[position].temat
        holder.textViewObecnosc.text = obecnosc
    }

    override fun getItemCount()=spotkania.count()

}