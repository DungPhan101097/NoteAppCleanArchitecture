package com.example.lap10715.notesappcleanarchitecture.presentation.contract;

import com.example.lap10715.notesappcleanarchitecture.presentation.model.NotePresentationModel;
import com.example.lap10715.notesappcleanarchitecture.presentation.view.IView;

public interface ShowNotesContract {
    interface View extends IView{
        void clearNoteList();
        void showNewNote(NotePresentationModel noteModel);
        void showList();
        void showEmptyPlaceholder();
    }

    interface Presenter{
        void populateNotesFromPrefs();
        void showListOrEmptyPlaceholder(int oldItemCount, int newItemCount);
        void deleteNoteFromMemory(NotePresentationModel noteModel);
    }
}
