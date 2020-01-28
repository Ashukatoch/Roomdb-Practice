package com.example.roomdb.NoteDao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdb.Entity.Note;

import java.util.List;

@Dao
public interface Note_dao
{
    @Insert
    void insert(Note note);
    @Update
    void update(Note note);
    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteallnotes();

    @Query("SELECT * FROM  note_table ORDER BY priority DESC")
    LiveData<List<Note>> selectallnotes();

}