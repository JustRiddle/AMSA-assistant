package com.example.assistant

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.assistant.Model.StudenciWGrupie
import com.example.assistant.Model.Student
import kotlinx.android.synthetic.main.recycler_item_studenci.view.*

class AdapterStudenciWGrupie:RecyclerView.Adapter<AdapterStudenciWGrupie.Holder>() {
    private var studenci = emptyList<Student>()
    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewImie: TextView
        val textViewNazwisko: TextView
        val textViewNrAlbumu: TextView

        init {
            textViewImie = itemView.findViewById<TextView>(R.id.textView_Imie)
            textViewNazwisko = itemView.findViewById<TextView>(R.id.textView_Nazwisko)
            textViewNrAlbumu = itemView.findViewById<TextView>(R.id.textView_NrAlbumu)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_item_studenci, parent,false) as View

        return Holder(view)

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.textViewNazwisko.text=studenci[position].nazwisko
        holder.textViewImie.text=studenci[position].imie
        holder.textViewNrAlbumu.text=studenci[position].nr_albumu

        holder.itemView.item_student.setOnClickListener {
            val action = fragment_grupa_detailsDirections.actionFragmentGrupaDetailsToFragmentStudentDetails(studenci[position])
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount()=studenci.count()

    fun setData(studenci_zewn: List<StudenciWGrupie>){
        this.studenci = studenci_zewn[0].studenci
        notifyDataSetChanged()
    }

}