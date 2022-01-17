package com.example.assistant.ViewModel

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.assistant.Model.Spotkanie
import com.example.assistant.R
import com.example.assistant.fragment_grupa_detailsDirections

class AdapterSpotkania():RecyclerView.Adapter<AdapterSpotkania.Holder>() {
    private var spotkania = emptyList<Spotkanie>()
    private var studentID = 0
    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewData: TextView
        val textViewTemat: TextView
        val textViewObecnosc: TextView


        init {
            textViewData = itemView.findViewById<TextView>(R.id.textView_Data)
            textViewTemat = itemView.findViewById<TextView>(R.id.textView_Temat)
            textViewObecnosc = itemView.findViewById<TextView>(R.id.textView_obecnosc)

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

        if(studentID == 0) {
            holder.textViewObecnosc.text = ""
        }
        else {
            if(spotkania[position].obecnosc.contains(studentID)) {
                holder.textViewObecnosc.setTextColor(Color.parseColor("#75e900"))
                holder.textViewObecnosc.text = "✔"
            }
            else{

                holder.textViewObecnosc.setTextColor(Color.parseColor("#B71C1C"))
                holder.textViewObecnosc.text = "X"
            }
        }


        holder.itemView.setOnClickListener {
            if(studentID == 0) {
                val action =
                    fragment_grupa_detailsDirections.actionFragmentGrupaDetailsToFragmentEdytujSpotkanie(
                        spotkania[position]
                    )
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount()=spotkania.count()

    fun setData(spotkania_zewn: List<Spotkanie>, studentId: Int = 0){
        this.spotkania = spotkania_zewn
        this.studentID = studentId
        Log.d("MOJE" ,"SETDATA")

        notifyDataSetChanged()
    }

}