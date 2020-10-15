package com.artem.notepad.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.artem.notepad.MainFragmentDirections
import com.artem.notepad.R
import com.artem.notepad.database.Note
import kotlinx.android.synthetic.main.list_item.view.*


class DataAdapter: RecyclerView.Adapter<DataAdapter.ViewHolder>(){

    private var noteList = emptyList<Note>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}


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

    override fun getItemCount(): Int = noteList.size //Возвращает размер коллекции


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Устанавливает необходимые
        //данные у созданых ViewHolder-ов
        
        val currentItem = noteList[position]

        holder.itemView.list_title.text = currentItem.param_head
        holder.itemView.list_description.text = currentItem.param_description

        holder.itemView.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
    }



/*
        fun bind(currentItem:Note){

            if (currentItem.param_head.length > 60) {

                currentItem.param_head = currentItem.param_head.substring(0, 60)
            }
            else {
                listTitleView?.text = currentItem.param_head
            }
            if (note.param_description.length> 90) {
                listDescriptionView?.text = currentItem.param_description.substring(0, 90)
            }
            else {
                listDescriptionView?.text = currentItem.param_description
            }
        }



*/


    }

    fun setData(note:List<Note>){
        this.noteList = note
        notifyDataSetChanged()
    }
}


