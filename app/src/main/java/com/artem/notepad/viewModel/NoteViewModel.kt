package com.artem.notepad.viewModel



import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.artem.notepad.database.Note
import com.artem.notepad.database.NoteDatabase
import com.artem.notepad.database.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch




class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData:LiveData<List<Note>>
    private val repository:NoteRepository


    private val _eventCreateNoteFinish = MutableLiveData<Boolean>()
    val eventCreateNoteFinish: LiveData<Boolean>
        get() = _eventCreateNoteFinish


    init {
        val noteDao= NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        readAllData = repository.readAllData
    }



    fun addNote(note:Note){
        viewModelScope.launch(Dispatchers.IO) { repository.addNote(note) }
    }

    fun updateNote(note:Note){
        viewModelScope.launch(Dispatchers.IO) { repository.updateNote(note) }
    }

    fun deleteNote(note:Note){
        viewModelScope.launch(Dispatchers.IO) { repository.deleteNote(note) }
    }


     fun onCreateNoteFinish() {
        _eventCreateNoteFinish.value = true
    }


    fun onCreateNoteFinishComplete() {
        _eventCreateNoteFinish.value = false
    }


}
