package com.artem.notepad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView


public class DataAdapter(private val dataSet:List<Note>):RecyclerView.Adapter<DataAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {                            //Создает новый объект ViewHolder
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)//когда RecyclerView нуждается в этом
        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int = dataSet.size //Возвращает размер коллекции


    override fun onBindViewHolder(holder: ViewHolder, position: Int) { //Устанавливает необходимые
        val note: Note = dataSet[position]                             //данные у созданых ViewHolder-ов

        holder.bind(note)
    }


    public class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var listTitleView:TextView? = null
        var listDescriptionView:TextView? = null

        init {
            listTitleView = itemView.findViewById(R.id.list_title)  //Ссылки на элементы
            listDescriptionView = itemView.findViewById(R.id.list_description)


            itemView.setOnClickListener{
                val position:Int = adapterPosition
                Toast.makeText(itemView.context,"You clicked on item # ${position + 1}",Toast.LENGTH_SHORT).show()

                val activity = it.context
                (activity as MainActivity).navController.navigate(R.id.action_mainFragment_to_testFragment)


            }

        }

        fun bind(note:Note){
            listTitleView?.text = note.param_head
            listDescriptionView?.text = note.param_description
        }

    }


}


