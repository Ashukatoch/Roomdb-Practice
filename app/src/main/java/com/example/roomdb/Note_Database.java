package com.example.roomdb;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Context;
import android.os.AsyncTask;

import com.example.roomdb.Entity.Note;
import com.example.roomdb.NoteDao.Note_dao;

@Database(entities = Note.class,version=1)
public abstract class Note_Database extends RoomDatabase
{
    private static Note_Database instance;
    public abstract Note_dao note_dao();//to access database operations methods declared in notedao interface;
    public static synchronized Note_Database getInstance(Context context)
    {
        if(instance==null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    Note_Database.class,"note_database")
                    .fallbackToDestructiveMigration()//delete the database and start from scratch when database is changed
                    .addCallback(roomcallback).build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomcallback=new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db)
        {
            super.onCreate(db);
            new PopulatedbAsyncTask(instance).execute();

        }
    };

    private static class PopulatedbAsyncTask extends AsyncTask<Void,Void,Void>
    {
   private Note_dao note_dao;
   //can be a problem here....
   private PopulatedbAsyncTask(Note_Database note_database)
   {
       note_dao=note_database.note_dao();
   }
        @Override
        protected Void doInBackground(Void... voids)
        {
       note_dao.insert(new Note("Title1","Description1",1));
       note_dao.insert(new Note("Title2","Description2",2));
       note_dao.insert(new Note("Title3","Description3",3));
       return null;
        }
    }
}
