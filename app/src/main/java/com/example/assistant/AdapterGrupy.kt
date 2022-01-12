package com.example.assistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.assistant.Model.Grupa
import com.example.assistant.Model.Student
import kotlinx.android.synthetic.main.recycler_item_grupy.view.*
import kotlinx.android.synthetic.main.recycler_item_studenci.view.*

class AdapterGrupy:RecyclerView.Adapter<AdapterGrupy.Holder>() {
    private var grupy = emptyList<Grupa>()
    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewNazwa: TextView
        val textViewDzien: TextView
        val textViewGodziny: TextView

        init {
            textViewNazwa = itemView.findViewById<TextView>(R.id.textView_Nazwa)
            textViewDzien = itemView.findViewById<TextView>(R.id.textView_Dzien)
            textViewGodziny = itemView.findViewById<TextView>(R.id.textView_Godziny)

            itemView.setOnClickListener {
                it.findNavController().navigate(R.id.action_fragment_tabs_to_fragment_grupa_details)
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

        holder.itemView.item_grupa.setOnClickListener {
            val action = fragment_tabsDirections.actionFragmentTabsToFragmentGrupaDetails(grupy[position])
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount()=grupy.count()

    fun setData(grupy_zewn: List<Grupa>){
        this.grupy = grupy_zewn
        notifyDataSetChanged()
    }
}