package com.TechInfinityStudios.smartplanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{
private List<Note> noteList;
private DatabaseHelper dbHelper;

    public NoteAdapter(List<Note> noteList, DatabaseHelper dbHelper) {
        this.noteList = noteList;
        this.dbHelper = dbHelper;
    }

    public NoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteAdapter.NoteViewHolder(view);
    }
    private NoteAdapter.OnNoteLongClickListener longClickListener;
    public interface OnNoteLongClickListener {
        void onNoteLongClick(Note note, int position);
    }

    public void setOnNoteLongClickListener(NoteAdapter.OnNoteLongClickListener listener) {
        this.longClickListener = listener;
    }
    public interface OnNoteClickListener {
        void onNoteClick(Note note, int position);
    }

    private OnNoteClickListener clickListener;

    public void setNoteOnClickListener(OnNoteClickListener listener) {
        this.clickListener = listener;
    }


    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.noteTitle.setText(note.getTitle());
        holder.noteText.setText(note.getText());

        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onNoteLongClick(note, position);
            }
            return true;
        });
        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onNoteClick(note, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle, noteText;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteText = itemView.findViewById(R.id.noteText);
            noteTitle = itemView.findViewById(R.id.noteTitle);
        }
    }
}
