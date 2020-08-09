package com.artem.notepad

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    private val noteTest = listOf(    //Тестовый массив с данными
        Note("Заголовок 1", "Тут описание заголовка 1"),
        Note("Заголовок 2", "Тут описание заголовка 2"),
        Note("Заголовок 3", "Тут описание заголовка 3"),
        Note("Заголовок 4", "Тут описание заголовка 4"),
        Note("Заголовок 5", "Тут описание заголовка 5"),
        Note("Заголовок 6", "Тут описание заголовка 6"),
        Note("Заголовок 7", "Тут описание заголовка 7"),
        Note("Заголовок 8", "Тут описание заголовка 8"),
        Note("Заголовок 9", "Тут описание заголовка 9"),
        Note("Заголовок 10", "Тут описание заголовка 10"),
        Note("Заголовок 11", "Тут описание заголовка 11"),
        Note("Заголовок 12", "Тут описание заголовка 12"),
        Note("Заголовок 13", "Тут описание заголовка 13"),
        Note("Заголовок 14", "Тут описание заголовка 14"),
        Note("Заголовок 15", "Тут описание заголовка 15"),
        Note("Заголовок 16", "Тут описание заголовка 16"),
        Note("Заголовок 17", "Тут описание заголовка 17")

    )


    private lateinit var recyclerView: RecyclerView
    private lateinit var rootView: View




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_main, container, false)
        recyclerView = rootView.findViewById(R.id.list_recycle_view)


        recyclerView.layoutManager = LinearLayoutManager(activity)//Вертикальный список
        recyclerView.adapter = DataAdapter(noteTest)              //Новый адаптер для установки

        return rootView
    }

    override fun onStart() {
        super.onStart()


        Fmain_Btn_Next.setOnClickListener{
            (activity as MainActivity).navController.navigate(R.id.action_mainFragment_to_testFragment)//Слушатель на кнопке
                                                                                                       //для переключения между
                                                                                                       //фрагментами
        }


    }


}