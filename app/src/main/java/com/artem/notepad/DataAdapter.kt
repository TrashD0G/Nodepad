package com.artem.notepad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


public class DataAdapter(private val dataSet:List<Note>):RecyclerView.Adapter<DataAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { //Создает новый объект ViewHolder
        val inflater = LayoutInflater.from(parent.context)                          //когда RecyclerView нуждается в этом
        return ViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = dataSet.size //Возвращает размер коллекции


    override fun onBindViewHolder(holder: ViewHolder, position: Int) { //Устанавливает необходимые
        val note: Note = dataSet[position]                             //данные у созданых ViewHolder-ов
        holder.bind(note)
    }


    public class ViewHolder(inflater: LayoutInflater,parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item,parent,false)) {

        var listTitleView:TextView? = null
        var listDescriptionView:TextView? = null


        init {
            listTitleView = itemView.findViewById(R.id.list_title)  //Ссылки на элементы
            listDescriptionView = itemView.findViewById(R.id.list_description)
        }

        fun bind(note:Note){
            listTitleView?.text = note.param_head
            listDescriptionView?.text = note.param_description
        }

    }


}


