package com.example.roomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.roomdb.Entity.Note;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private NoteViewModel noteViewModel;
    private List<Note> allnotes;
    private RecyclerView recyclerView;
    private Note_Adapter note_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.main_recyclerView_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        note_adapter=new Note_Adapter();
        recyclerView.setAdapter(note_adapter);
        noteViewModel= ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getallnotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes)
            {
                Toast.makeText(MainActivity.this, "OnChanged", Toast.LENGTH_LONG).show();
                note_adapter.setNotes(notes);
            }
        });
    }
}
