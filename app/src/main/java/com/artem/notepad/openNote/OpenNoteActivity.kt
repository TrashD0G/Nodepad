package com.artem.notepad.openNote


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.artem.notepad.R
import com.artem.notepad.databinding.ActivityOpenNoteBinding


class OpenNoteActivity : AppCompatActivity() {

    private lateinit var openNoteModel: OpenNoteModel
    private lateinit var binding: ActivityOpenNoteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_open_note)

        val activity = this
        val notePosition = intent.getIntExtra("itemPosition",0) //Получение номера позиции элемента


        openNoteModel = OpenNoteModel(binding,notePosition)

        binding.activityBtnDelete.setOnClickListener{ openNoteModel.noteDelete(activity,notePosition) }
        binding.activityBtnOk.setOnClickListener{ openNoteModel.noteChange(activity,notePosition) }


        lifecycle.addObserver(openNoteModel)
    }



}