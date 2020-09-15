package com.artem.notepad.openNote

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.artem.notepad.Note
import com.artem.notepad.NoteList
import com.artem.notepad.databinding.ActivityOpenNoteBinding
import com.artem.notepad.recyclerView


class OpenNoteModel(param_binding: ActivityOpenNoteBinding,param_notePosition: Int) : LifecycleObserver {

    private val binding = param_binding
    private val position = param_notePosition


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun bindingNote(){
        // Установка значений в EditText при открытии активити

        val noteDataContainer = NoteList.get(position) //Получение объекта Note по номеру позиции

        val getHead:String = noteDataContainer.param_head //Получение введенных данных
        val getDescription:String = noteDataContainer.param_description


        binding.activityNoteHead.setText(getHead)//Установка данных
        binding.activityNoteDescription.setText(getDescription)
    }


    fun noteChange(activity: OpenNoteActivity, position: Int){

        if (binding.activityNoteHead.text.trim().isEmpty() and binding.activityNoteDescription.text.trim().isEmpty()) {
            //Если при изменении заметки пользователь всё стёр заметка удаляется
            noteDelete(activity,position)

        } else {
            NoteList.set(position,
                Note(binding.activityNoteHead.text.toString(),binding.activityNoteDescription.text.toString())
            ) //Меняем значение
            recyclerView.adapter?.notifyItemChanged(position)                                                                     //Обновляем изменненый элемент в списке
            activity.finish()
        }

        hideKeyboardActivity(activity)
    }



    fun noteDelete(activity: OpenNoteActivity, position: Int){
        //Удаление заметки
        NoteList.removeAt(position)
        recyclerView.adapter?.notifyItemRemoved(position)

        activity.finish()
    }



    private fun hideKeyboardActivity(activity: OpenNoteActivity){
        //Закрытие клавиатуры
        val view = activity.currentFocus
        if (view != null) {
            val hideMe = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideMe.hideSoftInputFromWindow(view.windowToken,0)
        }
    }
}