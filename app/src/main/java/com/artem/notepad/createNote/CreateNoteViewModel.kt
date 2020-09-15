package com.artem.notepad.createNote


import android.app.Application
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.artem.notepad.Note
import com.artem.notepad.NoteList


class CreateNoteViewModel(application: Application, private val head: EditText, private val description:EditText) : AndroidViewModel(application) {

  private val _eventCreateNoteFinish = MutableLiveData<Boolean>()
    val eventCreateNoteFinish: LiveData<Boolean>
        get() = _eventCreateNoteFinish


    private fun createNote(head:String,description:String){ //Создание заметок
        NoteList.add(Note(head,description))
    }


    private fun onCreateNoteFinish() {
        _eventCreateNoteFinish.value = true
    }


     fun inputChecker(){
        //Проверка на ввод

        if (head.text.toString().trim().isNotEmpty() or description.text.toString().trim().isNotEmpty()) {
            createNote(head.text.toString(),description.text.toString())
            onCreateNoteFinish()
        }
         else{ Log.i("MyTag", "head:$head")}
    }



    fun onCreateNoteFinishComplete() {
        _eventCreateNoteFinish.value = false
    }
}
