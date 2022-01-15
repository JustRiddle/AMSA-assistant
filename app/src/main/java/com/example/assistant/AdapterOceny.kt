package com.example.assistant

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.assistant.Model.Ocena
import com.example.assistant.Model.Spotkanie
import com.example.assistant.Model.Student
import kotlinx.android.synthetic.main.recycler_item_grupy.view.*
import kotlinx.android.synthetic.main.recycler_item_ocena.view.*

class AdapterOceny():RecyclerView.Adapter<AdapterOceny.Holder>() {

    private var oceny = emptyList<Ocena>()
    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewOcena: TextView
        val textViewKomentarz: TextView


        init {
            textViewOcena = itemView.findViewById<TextView>(R.id.textView_ocena)
            textViewKomentarz = itemView.findViewById<TextView>(R.id.textView_zaCo)
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

        holder.itemView.item_ocena.setOnClickListener {
            val action = fragment_student_detailsDirections.actionFragmentStudentDetailsToFragmentEdytujOcene(oceny[position])
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount()=oceny.count()

    fun setData(oceny_zewn: List<Ocena>){
        Log.d("MOJE", "AdapterOceny dostał: "+oceny_zewn.toString())
        this.oceny = oceny_zewn
        notifyDataSetChanged()
    }

}