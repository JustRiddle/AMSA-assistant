package com.example.assistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assistant.Model.*
import kotlinx.android.synthetic.main.recycler_item_zapisy.view.*
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
class AdapterEnrolment(private val listener: RecyclerStudentCallback): RecyclerView.Adapter<AdapterEnrolment.Holder>() {


    private var studenci = emptyList<Student>()
    private var currentGroup = Grupa(0,"placeholder","","","")


    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewImie: TextView
        val textViewNazwisko: TextView
        val textViewNrAlbumu: TextView

        init {

            textViewImie = itemView.findViewById<TextView>(R.id.textView_Imie_enr)
            textViewNazwisko = itemView.findViewById<TextView>(R.id.textView_Nazwisko_enr)
            textViewNrAlbumu = itemView.findViewById<TextView>(R.id.textView_NrAlbumu_enr)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_item_zapisy, parent,false) as View

        return Holder(view)

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.textViewNazwisko.text=studenci[position].nazwisko
        holder.textViewImie.text=studenci[position].imie
        holder.textViewNrAlbumu.text=studenci[position].nr_albumu

        holder.itemView.item_enrolment.btn_enrol.setOnClickListener {
            listener.onItemClick(studenci[position])
        }

    }

    override fun getItemCount()=studenci.count()

    fun setData(studenci_zewn: List<Student>, grupa: Grupa){
        this.studenci = studenci_zewn
        this.currentGroup = grupa
        notifyDataSetChanged()
    }

}