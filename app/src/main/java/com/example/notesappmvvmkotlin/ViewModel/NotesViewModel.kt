package com.example.notesappmvvmkotlin.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesappmvvmkotlin.Database.NotesDatabase
import com.example.notesappmvvmkotlin.Model.Notes
import com.example.notesappmvvmkotlin.Repository.NotesRepository

class NotesViewModel(application: Application):AndroidViewModel(application) {
    private val repository: NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNoteDao()
        repository = NotesRepository(dao)

    }

    fun addNotes(notes: Notes) {
        repository.insertNotes(notes)
    }

    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()

    fun deleteNotes(id: Int){
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes) {
        repository.updateNotes(notes)
    }




}