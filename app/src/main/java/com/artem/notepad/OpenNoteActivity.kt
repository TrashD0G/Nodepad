package com.artem.notepad

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText


class OpenNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_note)
    }

    override fun onStart() {
        super.onStart()
        val btnFinish:Button = findViewById(R.id.activity_Btn_ok)
        val btnDelete:Button = findViewById(R.id.activity_Btn_delete)

        val notePosition:Int = intent.getIntExtra("itemPosition",0) //Получение номера позиции элемента


        bindingNote(notePosition)

        btnDelete.setOnClickListener{noteDelete(notePosition)}
        btnFinish.setOnClickListener{noteChange(notePosition)}
    }


    private fun bindingNote(position:Int){
        // Установка значений в EditText при открытии активити
        val headEditText: EditText = findViewById(R.id.activity_note_head)
        val descriptionEditText: EditText = findViewById(R.id.activity_note_description)


        val noteDataContainer = NoteList.get(position) //Получение объекта Note по номеру позиции

        val getHead:String = noteDataContainer.param_head //Получение введенных данных
        val getDescription:String = noteDataContainer.param_description

        headEditText.setText(getHead)//Установка данных
        descriptionEditText.setText(getDescription)
    }

    private fun noteChange(position: Int){
        val headEditText: EditText = findViewById(R.id.activity_note_head)
        val descriptionEditText: EditText = findViewById(R.id.activity_note_description)

        if (headEditText.text.trim().isEmpty() and descriptionEditText.text.trim().isEmpty()) {
            //Если при изменении заметки пользователь всё стёр заметка удаляется
            noteDelete(position)
        } else {
            NoteList.set(position,Note(headEditText.text.toString(),descriptionEditText.text.toString())) //Меняем значение
            recyclerView.adapter?.notifyItemChanged(position)                                             //Обновляем изменненый элемент в списке
            finish()

        }

        hideKeyboardActivity()
    }

    private fun noteDelete(position: Int){
        //Удаление заметки
        NoteList.removeAt(position)
        recyclerView.adapter?.notifyItemRemoved(position)
        finish()
    }


    private fun hideKeyboardActivity(){
        //Закрытие клавиатуры
        val view = this.currentFocus
        if (view != null) {
            val hideMe = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideMe.hideSoftInputFromWindow(view.windowToken,0)
        }
    }

}