package com.example.assistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.assistant.Model.Ocena
import com.example.assistant.Model.Spotkanie
import com.example.assistant.Model.Student

class AdapterOceny(val oceny: List<Ocena>):RecyclerView.Adapter<AdapterOceny.Holder>() {
    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewOcena: TextView
        val textViewKomentarz: TextView


        init {
            textViewOcena = itemView.findViewById<TextView>(R.id.textView_ocena)
            textViewKomentarz = itemView.findViewById<TextView>(R.id.textView_zaCo)

            itemView.setOnClickListener {
                // TODO Wrzucić tutaj nav do widoku obecności
                //  it.findNavController().navigate(R.id.action_fragment_tabs_to_fragment_student_details)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_item_ocena, parent,false) as View

        return Holder(view)

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.textViewOcena.text=oceny[position].ocena
        holder.textViewKomentarz.text=oceny[position].komentarz
    }

    override fun getItemCount()=oceny.count()

}