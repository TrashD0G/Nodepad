package com.artem.notepad


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_create_note.*


class CreateNoteFragment : Fragment() {

    private lateinit var CreateHead:EditText
    private lateinit var CreateDescription:EditText
    private lateinit var rootView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_create_note, container, false)

        CreateHead = rootView.findViewById(R.id.fragment_note_head)
        CreateDescription = rootView.findViewById(R.id.fragment_note_description)


        return rootView
    }


    override fun onStart() {
        super.onStart()
        fragment_Btn_ok.setOnClickListener{

            inputChecker()
            (activity as MainActivity).navController.navigate(R.id.action_testFragment_to_mainFragment)
        }

    }


    private fun inputChecker(){ //Проверка на ввод

        val HEAD:String = CreateHead.text.toString()
        val DESCRIPTION:String = CreateDescription.text.toString()

        if (HEAD.trim().isNotEmpty() or DESCRIPTION.trim().isNotEmpty()){
            createNote(HEAD,DESCRIPTION)
        }
    }

    private fun createNote(head:String,description:String){ //Создание заметок
        NoteList.add(Note(head,description))
    }



}