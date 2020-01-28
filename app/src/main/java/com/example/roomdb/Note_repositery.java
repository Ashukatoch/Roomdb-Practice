package com.example.roomdb;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.roomdb.Entity.Note;
import com.example.roomdb.NoteDao.Note_dao;

import java.util.List;
//it provides abstraction layer over different data sources
public class Note_repositery
{
    private Note_dao note_dao;
    private LiveData<List<Note>> allNotes;

    public Note_repositery(Application application)//application is  subclass of context
    {
Note_Database note_database=Note_Database.getInstance(application);
note_dao=note_database.note_dao();
allNotes=note_dao.selectallnotes();

    }
    public void insert(Note note)
    {
new InsertNoteAsyncTask(note_dao).execute(note);
    }
    public void delete(Note note)
    {
new DeleteNoteAsyncTask(note_dao).execute(note);
    }
    public void update(Note note)
    {
new UpdateNoteAsyncTask(note_dao).execute(note);
    }
    public void deleteallnotes()
    {
new DeleteallNotesAsyncTask(note_dao).execute();
    }
    public LiveData<List<Note>> getallnotes()
    {
return allNotes;
    }
    private static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void>
    {
   private Note_dao note_dao;
   private InsertNoteAsyncTask(Note_dao note_dao)
   {
       this.note_dao=note_dao;
   }
        @Override
        protected Void doInBackground(Note... notes)
        {
       note_dao.insert(notes[0]);
            return null ;
        }
    }
    private static class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void>
    {
        private Note_dao note_dao;
        private DeleteNoteAsyncTask(Note_dao note_dao)
        {
            this.note_dao=note_dao;
        }
        @Override
        protected Void doInBackground(Note... notes)
        {
            note_dao.delete(notes[0]);
            return null ;
        }
    }
    private static class UpdateNoteAsyncTask extends AsyncTask<Note,Void,Void>
    {
        private Note_dao note_dao;
        private UpdateNoteAsyncTask(Note_dao note_dao)
        {
            this.note_dao=note_dao;
        }
        @Override
        protected Void doInBackground(Note... notes)
        {
            note_dao.update(notes[0]);
            return null ;
        }
    }
    private static class DeleteallNotesAsyncTask extends AsyncTask<Void,Void,Void>
    {
        private Note_dao note_dao;
        private DeleteallNotesAsyncTask(Note_dao note_dao)
        {
            this.note_dao=note_dao;
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            note_dao.deleteallnotes();
            return null;
        }
    }


}
