package com.artem.notepad


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_create_note.*


class CreateNoteFragment : Fragment() {

    private lateinit var createHead:EditText
    private lateinit var createDescription:EditText
    private lateinit var rootView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_create_note, container, false)

        createHead = rootView.findViewById(R.id.fragment_note_head)
        createDescription = rootView.findViewById(R.id.fragment_note_description)


        return rootView
    }


    override fun onStart() {
        super.onStart()
        fragment_Btn_ok.setOnClickListener{

            inputChecker()
            hideKeyboardFragment()
            (activity as MainActivity).navController.navigate(R.id.action_testFragment_to_mainFragment)
        }

    }


    private fun inputChecker(){
        //Проверка на ввод

        val head:String = createHead.text.toString()
        val description:String = createDescription.text.toString()

        if (head.trim().isNotEmpty() or description.trim().isNotEmpty()) {
            createNote(head,description)
        }
    }

    private fun createNote(head:String,description:String){ //Создание заметок
        NoteList.add(Note(head,description))
    }

    private fun hideKeyboardFragment(){
        val inputManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusedView = activity?.currentFocus

        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(
                currentFocusedView.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

}