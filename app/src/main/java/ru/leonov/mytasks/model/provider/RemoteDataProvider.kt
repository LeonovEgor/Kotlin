package ru.leonov.mytasks.model.provider

import androidx.lifecycle.LiveData
import ru.leonov.mytasks.model.data.NoteResult
import ru.leonov.mytasks.model.entities.Note

interface RemoteDataProvider {
    fun subsrcibeToAllNotes(): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
}