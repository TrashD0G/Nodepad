package com.artem.notepad.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


//Объект для хранения в БД
@Parcelize // Для передачи объекта
@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val param_head: String,
    val param_description: String
) : Parcelable


