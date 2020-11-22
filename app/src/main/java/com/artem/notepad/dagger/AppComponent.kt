package com.artem.notepad.dagger

import com.artem.notepad.MainFragment
import com.artem.notepad.createNote.CreateNoteFragment
import com.artem.notepad.viewModel.NoteViewModel
import dagger.Component


@Component(modules = [AppModule::class])
interface AppComponent {

    fun injectMainFragment(mainFragment: MainFragment)
    
}