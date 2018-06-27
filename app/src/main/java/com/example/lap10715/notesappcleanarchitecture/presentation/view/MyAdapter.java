package com.example.lap10715.notesappcleanarchitecture.presentation.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lap10715.notesappcleanarchitecture.R;
import com.example.lap10715.notesappcleanarchitecture.presentation.model.NotePresentationModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.NoteViewHolder> implements ItemDismissHelper{

    private List<NotePresentationModel> mNoteList;
    private NoteObserver mNoteObserver;

    public MyAdapter() {
        mNoteList = new ArrayList<>();
    }

    public void setNoteObserver(NoteObserver mNoteObserver) {
        this.mNoteObserver = mNoteObserver;
    }

    private void notifyOnNoteListChange(int oldItemCount, int newItemCount) {
        if(mNoteObserver != null){
            mNoteObserver.onNoteListChange(oldItemCount, newItemCount);
        }
    }

    private void notifyDeleteItem(NotePresentationModel noteModel){
        if(mNoteObserver != null){
            mNoteObserver.onItemDelete(noteModel);
        }
    }

    public void addNote(NotePresentationModel noteModel){
        mNoteList.add(noteModel);
        notifyItemInserted(mNoteList.size() - 1);

        notifyOnNoteListChange(mNoteList.size() -1, mNoteList.size());
    }

    @Override
    public void onDismissItem(int pos) {
        NotePresentationModel noteModel = mNoteList.remove(pos);
        notifyItemRemoved(pos);

        notifyOnNoteListChange(mNoteList.size() + 1, mNoteList.size());
        notifyDeleteItem(noteModel);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_note,
                parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        NotePresentationModel noteModel = mNoteList.get(position);
        holder.bindData(noteModel);
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    public void clearList(){
        mNoteList.clear();
        notifyDataSetChanged();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvTitle, tvContent, tvDate;

        public NoteViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDate = itemView.findViewById(R.id.tv_date);
        }

        void bindData(NotePresentationModel note){
            tvTitle.setText(note.getTitle());
            tvContent.setText(note.getContent());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM - HH:mm");
            String date = simpleDateFormat.format(note.getCreateAt());

            tvDate.setText(date);
        }
    }

    public interface NoteObserver{
        void onNoteListChange(int oldItemCount, int newItemCount);

        void onItemDelete(NotePresentationModel noteModel);
    }
}
