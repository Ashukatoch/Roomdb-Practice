package com.example.roomdb;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdb.Entity.Note;
import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Note_Adapter extends RecyclerView.Adapter<Note_Adapter.NoteViewHolder>
{


    List<Note> allnotes=new ArrayList<>();
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new NoteViewHolder(v);
    }                

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position)
    {
        Log.d("Bind","Entered");
        holder.setTitle(allnotes.get(position).getTitle());
        holder.setDesc(allnotes.get(position).getDescription());
        holder.setPriority(allnotes.get(position).getPriority());
    }

    @Override
    public int getItemCount() {
        return allnotes.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder
    {
        View v;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            v=itemView;
        }
        public void setTitle(String title)
        {
            TextView title_tv=v.findViewById(R.id.title_tv_id);
            title_tv.setText("Title:"+" "+title);
        }
        public void setDesc(String desc)
        {
            TextView desc_tv=v.findViewById(R.id.desc_tv_id);
            desc_tv.setText("DEscription:"+" "+desc);
        }
        public void setPriority(int priority)
        {
            TextView priority_tv=v.findViewById(R.id.priority_tv_id);
            priority_tv.setText(String.valueOf(priority));
        }
    }
    public void setNotes(List<Note> allnotes)
    {
        this.allnotes=allnotes;
        notifyDataSetChanged();
    }
}
