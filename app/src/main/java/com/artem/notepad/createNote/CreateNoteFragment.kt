package com.artem.notepad.createNote


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.artem.notepad.MainActivity
import com.artem.notepad.R
import com.artem.notepad.databinding.FragmentCreateNoteBinding


class CreateNoteFragment : Fragment() {

    private lateinit var binding: FragmentCreateNoteBinding
    private lateinit var viewModel:CreateNoteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,R.layout.fragment_create_note,container,false
        )

        val head = binding.fragmentNoteHead
        val description = binding.fragmentNoteDescription

        viewModel = ViewModelProvider(this,CreateNoteViewModelFactory(activity?.application!!,head,description)).get(CreateNoteViewModel::class.java)
        binding.createNoteViewModel = viewModel

        viewModel.eventCreateNoteFinish.observe(viewLifecycleOwner,
            { hasFinished -> if (hasFinished) createNoteFinished() })

        viewModel.eventInputCheckerEmpty.observe(viewLifecycleOwner,
            {hasEmpty -> if (hasEmpty) createNoteIsEmpty()})

        return binding.root
    }


    private fun createNoteFinished(){
        hideKeyboardFragment()
        viewModel.onCreateNoteFinishComplete()

        (activity as MainActivity).navController.navigate(R.id.action_testFragment_to_mainFragment)
    }

    private fun createNoteIsEmpty(){
        hideKeyboardFragment()
        (activity as MainActivity).navController.navigate(R.id.action_testFragment_to_mainFragment)
        Toast.makeText(this.context,"Нельзя создавать пустые заметки!",Toast.LENGTH_LONG).show()
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