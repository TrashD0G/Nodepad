package com.artem.notepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


class OpenNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_note)
    }

    override fun onStart() {
        super.onStart()
        val BtnFinish:Button = findViewById(R.id.activity_Btn_ok)
        val NotePosition:Int = intent.getIntExtra("itemPosition",0) //Получение номера позиции элемента


        BindingNote(NotePosition)
        BtnFinish.setOnClickListener{NoteChange(NotePosition)}
    }


    private fun BindingNote(position:Int){ // Установка значений в EditText при открытии активити
        val headEditText: EditText = findViewById(R.id.activity_note_head)
        val descriptionEditText: EditText = findViewById(R.id.activity_note_description)


        val NoteDataContainer = NoteList.get(position) //Получение объекта Note по номеру позиции

        val getHead:String = NoteDataContainer.param_head //Получение введенных данных
        val getDescription:String = NoteDataContainer.param_description

        headEditText.setText(getHead)//Установка данных
        descriptionEditText.setText(getDescription)
    }

private fun NoteChange(position: Int){
    val headEditText: EditText = findViewById(R.id.activity_note_head)
    val descriptionEditText: EditText = findViewById(R.id.activity_note_description)


    NoteList.set(position,Note(headEditText.text.toString(),descriptionEditText.text.toString())) //Меняем значение
    recyclerView.adapter?.notifyItemChanged(position)                                             //Обновляем изменненый элемент в списке

    finish()//Закрытие активити
}

}