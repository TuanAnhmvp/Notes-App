package com.example.notesappmvvmkotlin.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesappmvvmkotlin.Model.Notes

@Dao
interface NoteDao {
    @Query("SELECT * FROM Notes")
    fun getAllNotes(): LiveData<List<Notes>>

    /*query get all priority = ?*/
    @Query("SELECT * FROm Notes WHERE priority = 3")
    fun getHighNotes() : LiveData<List<Notes>>

    @Query("SELECT * FROm Notes WHERE priority = 2")
    fun getMediumNotes() : LiveData<List<Notes>>

    @Query("SELECT * FROm Notes WHERE priority = 1")
    fun getLowNotes() : LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Query("DELETE FROM notes WHERE id =:id")
    fun deleteNotes(id: Int)

    @Update
    fun updateNotes(notes: Notes)
    
}