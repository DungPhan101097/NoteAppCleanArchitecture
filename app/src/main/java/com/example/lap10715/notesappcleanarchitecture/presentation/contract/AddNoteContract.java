package com.example.lap10715.notesappcleanarchitecture.presentation.contract;

import com.example.lap10715.notesappcleanarchitecture.presentation.model.NotePresentationModel;
import com.example.lap10715.notesappcleanarchitecture.presentation.view.IView;

public interface AddNoteContract {
    interface AddNoteContractView extends IView{
        void closeSectionWithPositionResult();
        void showError(String e);
    }

    interface AddNoteContractPresenter{
        void validateField(final String title, final String content);
        void saveNote(NotePresentationModel noteModel);
    }
}
