package com.artem.notepad

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artem.notepad.openNote.OpenNoteActivity



class DataAdapter(private val dataSet: List<Note>) : RecyclerView.Adapter<DataAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Создает новый объект ViewHolder
        //когда RecyclerView нуждается в этом
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent,
            false
        )
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = dataSet.size //Возвращает размер коллекции


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Устанавливает необходимые
        //данные у созданых ViewHolder-ов

        val note: Note = dataSet[position]
        holder.bind(note)
    }


     class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
         private var listTitleView:TextView? = null
         private var listDescriptionView:TextView? = null
         private var context:Context = itemView.context
         private val intent = Intent(context, OpenNoteActivity::class.java)


        init {

            listTitleView = itemView.findViewById(R.id.list_title)  //Ссылки на элементы
            listDescriptionView = itemView.findViewById(R.id.list_description)
            Log.i("MyTag","Инициализация вью холдера")




            itemView.setOnClickListener {

                val position: Int = adapterPosition
                intent.putExtra("itemPosition", position)
                context.startActivity(intent)

            }

        }

        fun bind(note: Note){

            if (note.param_head.length > 60) {
                listTitleView?.text= note.param_head.substring(0, 60)
            }
            else {
                listTitleView?.text = note.param_head
            }
            if (note.param_description.length> 90) {
                listDescriptionView?.text = note.param_description.substring(0, 90)
            }
            else {
                listDescriptionView?.text = note.param_description
            }
        }

    }


}


