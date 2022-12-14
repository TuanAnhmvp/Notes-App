package com.example.notesappmvvmkotlin.Repository

import androidx.lifecycle.LiveData
import com.example.notesappmvvmkotlin.Dao.NoteDao
import com.example.notesappmvvmkotlin.Model.Notes

class NotesRepository(private val dao: NoteDao) {
    fun getAllNotes(): LiveData<List<Notes>> {
        return dao.getAllNotes()
    }

    /*get filter notes*/
    fun getLowNotes(): LiveData<List<Notes>> = dao.getLowNotes()
    fun getMediumNotes(): LiveData<List<Notes>> = dao.getMediumNotes()
    fun getHighNotes(): LiveData<List<Notes>> = dao.getHighNotes()

    fun insertNotes(notes: Notes) {
        dao.insertNotes(notes)
    }

    fun deleteNotes(id: Int) {
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes) {
        dao.updateNotes(notes)
    }




}