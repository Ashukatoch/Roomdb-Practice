package com.example.roomdb;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomdb.Entity.Note;

import java.util.List;

public class NoteViewModel extends AndroidViewModel
{
    private Note_repositery note_repositery;
    private LiveData<List<Note>> allnotes;
    public NoteViewModel(@NonNull Application application)
    {
        super(application);
        note_repositery=new Note_repositery(application);
        allnotes=note_repositery.getallnotes();
    }
    public void insert(Note note)
    {
        note_repositery.insert(note);
    }
    public void delete(Note note)
    {
        note_repositery.delete(note);
    }
    public void update(Note note)
    {
        note_repositery.update(note);
    }
    public void deleteallnotes()
    {
        note_repositery.deleteallnotes();
    }
    public LiveData<List<Note>> getallnotes()
    {
        return allnotes;
    }


}
