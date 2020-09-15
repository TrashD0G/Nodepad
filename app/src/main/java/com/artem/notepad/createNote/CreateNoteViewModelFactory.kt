package com.artem.notepad.createNote

import android.app.Application
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CreateNoteViewModelFactory(private val application: Application, private val headText:EditText, private val descriptionText:EditText) : ViewModelProvider.AndroidViewModelFactory(application){


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreateNoteViewModel(application,headText,descriptionText) as T
    }
}