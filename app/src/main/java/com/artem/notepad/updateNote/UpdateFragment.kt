package com.artem.notepad.updateNote

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.artem.notepad.R
import com.artem.notepad.viewModel.NoteViewModel
import com.artem.notepad.database.Note
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

private val args by navArgs<UpdateFragmentArgs>() // доступ к аргументам
private lateinit var mNoteViewModel: NoteViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_update,container,false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)


        view.fragment_update_note_head.setText(args.currentNote.param_head)
        view.fragment_update_note_description.setText(args.currentNote.param_description)

        view.fragment_update_Btn_ok.setOnClickListener { inputChecker() }
        view.fragment_Btn_delete.setOnClickListener { deleteNote() }

        return view
    }


    private fun inputChecker(){
        val head = fragment_update_note_head.text.toString()
        val description = fragment_update_note_description.text.toString()

        if (head.trim().isNotEmpty() or description.trim().isNotEmpty()) {


         val updateNote = Note(args.currentNote.id,head,description)

            mNoteViewModel.updateNote(updateNote)
            hideKeyboardFragment()
            findNavController().navigate(R.id.action_updateFragment_to_mainFragment)

        }
        else{
            Toast.makeText(this.context, "Заметка пустая !", Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteNote(){
        mNoteViewModel.deleteNote(args.currentNote)
        Toast.makeText(requireContext(),"Записка удалена!",Toast.LENGTH_LONG).show()
        hideKeyboardFragment()
        findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
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