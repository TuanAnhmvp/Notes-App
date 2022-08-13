package com.example.notesappmvvmkotlin.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesappmvvmkotlin.Model.Notes

@Dao
interface NoteDao {
    @Query("SELECT * FROM Notes")
    fun getNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Query("DELETE FROM notes WHERE id =:id")
    fun deleteNotes(id: Int)

    @Update
    fun updateNotes(notes: Notes)
    
}