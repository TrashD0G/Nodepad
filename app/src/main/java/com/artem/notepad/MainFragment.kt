package com.artem.notepad

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main.*

lateinit var recyclerView: RecyclerView
val NoteList = ArrayList<Note>()//Лист для заметок
private lateinit var rootView: View

class MainFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_main, container, false)
        recyclerView = rootView.findViewById(R.id.list_recycle_view)

        recyclerView.layoutManager = LinearLayoutManager(activity)//Вертикальный список
        recyclerView.adapter = DataAdapter(NoteList)              //Новый адаптер для установки


        recyclerView.addItemDecoration(DividerItemDecoration(context,LinearLayoutManager.VERTICAL))//Линия разделение
                                                                                                   //между элементами списка


        return rootView
    }

    override fun onStart() {
        super.onStart()

        Btn_create_note.setOnClickListener{
            //Слушатель на кнопке для переключения между фрагментами

                (activity as MainActivity).navController.navigate(R.id.action_mainFragment_to_testFragment)
        }


    }


}