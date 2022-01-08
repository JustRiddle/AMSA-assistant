package com.example.assistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.assistant.Model.Grupa

class AdapterGrupy(val grupy: List<Grupa>):RecyclerView.Adapter<AdapterGrupy.Holder>() {
    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewNazwa: TextView
        val textViewDzien: TextView
        val textViewGodziny: TextView

        init {
            textViewNazwa = itemView.findViewById<TextView>(R.id.textView_Nazwa)
            textViewDzien = itemView.findViewById<TextView>(R.id.textView_Dzien)
            textViewGodziny = itemView.findViewById<TextView>(R.id.textView_Godziny)

            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Kliknieto", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_item_grupy, parent,false) as View

        return Holder(view)

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.textViewNazwa.text=grupy[position].Nazwa
        holder.textViewDzien.text=grupy[position].dzien_tygodnia
        holder.textViewGodziny.text=grupy[position].godz_od +" - "+ grupy[position].godz_do
    }

    override fun getItemCount()=grupy.count()

}