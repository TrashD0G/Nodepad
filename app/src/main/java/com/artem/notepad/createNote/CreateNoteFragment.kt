package com.artem.notepad.createNote


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.artem.notepad.R
import com.artem.notepad.database.Note
import com.artem.notepad.databinding.FragmentCreateNoteBinding
import com.artem.notepad.viewModel.NoteViewModel


class CreateNoteFragment : Fragment() {

    private lateinit var binding: FragmentCreateNoteBinding
    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_create_note, container, false
        )

        val head = binding.fragmentNoteHead
        val description = binding.fragmentNoteDescription

        //ViewModel
        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        mNoteViewModel.eventCreateNoteFinish.observe(viewLifecycleOwner,
            { hasFinished -> if (hasFinished) createNoteFinished() })

        binding.fragmentBtnOk.setOnClickListener { inputChecker(head, description) }

        return binding.root
    }


    private fun createNoteFinished(){
        hideKeyboardFragment()
        mNoteViewModel.onCreateNoteFinishComplete()

        findNavController().navigate(R.id.action_createNoteFragment_to_mainFragment)
    }


    private fun inputChecker(head: EditText, description: EditText){

        if (head.text.toString().trim().isNotEmpty() or description.text.toString().trim().isNotEmpty()) {

            val note = Note(0, head.text.toString(), description.text.toString())
            mNoteViewModel.addNote(note)
            mNoteViewModel.onCreateNoteFinish()



        }
        else{
            Toast.makeText(this.context, "Заметка пустая !", Toast.LENGTH_LONG).show()
        }
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