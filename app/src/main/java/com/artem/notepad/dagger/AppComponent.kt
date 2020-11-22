package com.artem.notepad.dagger

import com.artem.notepad.MainFragment

import dagger.Component


@Component(modules = [AppModule::class])
interface AppComponent {

    fun injectMainFragment(mainFragment: MainFragment)

}