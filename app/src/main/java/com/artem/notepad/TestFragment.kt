package com.artem.notepad


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_open_note.*


class TestFragment : Fragment() {

    private lateinit var CreateHead:EditText
    private lateinit var CreateDescription:EditText
    private lateinit var rootView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_open_note, container, false)

        CreateHead = rootView.findViewById(R.id.note_head)
        CreateDescription = rootView.findViewById(R.id.note_description)



        return rootView
    }


    override fun onStart() {
        super.onStart()
        Ftest_Btn_Back.setOnClickListener{
            createNote()
            (activity as MainActivity).navController.navigate(R.id.action_testFragment_to_mainFragment)
        }


    }


    private fun createNote(){ //Создание заметок
        val head:String = CreateHead.text.toString()
        val description:String = CreateDescription.text.toString()
        NoteList.add(Note(head,description))
    }




}