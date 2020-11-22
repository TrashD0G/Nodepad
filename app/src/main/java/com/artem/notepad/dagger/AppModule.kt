package com.artem.notepad.dagger

import com.artem.notepad.adapter.DataAdapter
import com.artem.notepad.viewModel.NoteViewModel
import dagger.Module
import dagger.Provides


@Module
class AppModule {


    @Provides
    fun provideRecyclerAdapter() : DataAdapter{
        return DataAdapter()
    }


}