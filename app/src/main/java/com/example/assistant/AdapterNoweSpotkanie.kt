package com.example.assistant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.assistant.Model.ObecnosciCallback
import com.example.assistant.Model.StudenciWGrupie
import com.example.assistant.Model.Student
import kotlinx.android.synthetic.main.recycler_item_studenci.view.*

class AdapterNoweSpotkanie(private val listener: ObecnosciCallback):RecyclerView.Adapter<AdapterNoweSpotkanie.Holder>() {
    private var studenci = emptyList<Student>()
    lateinit var context: Context
    private var obecnosci = mutableSetOf<Int>()
    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewImie: TextView
        val toggle: ToggleButton

        init {
            textViewImie = itemView.findViewById<TextView>(R.id.text_imieNazwisko_obecnosc)
            toggle = itemView.findViewById<ToggleButton>(R.id.toggleButton)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_item_obecnosc, parent,false) as View
            context = parent.context
        return Holder(view)

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.textViewImie.text=studenci[position].imie+" "+studenci[position].nazwisko
        if(obecnosci.contains(studenci[position].studentId))
        {
            holder.toggle.isChecked = true
        }

        holder.toggle.setOnClickListener {

            if(holder.toggle.isChecked)
            {
                obecnosci.add(studenci[position].studentId)
            }
            else{
                obecnosci.remove(studenci[position].studentId)
            }
            listener.getList(obecnosci)
        }
        
    }

    override fun getItemCount()=studenci.count()

    fun setData(studenci_zewn: List<Student>){
        this.studenci = studenci_zewn
        notifyDataSetChanged()
    }
    fun setObecnosci(obecnosci_zewn: MutableSet<Int>){
        this.obecnosci = obecnosci_zewn
        notifyDataSetChanged()
    }

}