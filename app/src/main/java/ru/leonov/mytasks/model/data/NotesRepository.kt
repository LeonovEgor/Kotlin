package ru.leonov.mytasks.model.data

import ru.leonov.mytasks.model.entities.Note
import ru.leonov.mytasks.model.provider.FireStoreProvider
import ru.leonov.mytasks.model.provider.RemoteDataProvider

class NotesRepository(val remoteProvider: RemoteDataProvider) {
    suspend fun saveNote(note: Note) = remoteProvider.saveNote(note)
    suspend fun getNoteById(id: String) = remoteProvider.getNoteById(id)
    fun getNotes() = remoteProvider.subscribeToAllNotes()
    suspend fun getCurrentUser() = remoteProvider.getCurrentUser()
    suspend fun deleteNote(noteId: String) = remoteProvider.deleteNote(noteId)
}