package com.example.lap10715.notesappcleanarchitecture.data.repository.api;

import com.example.lap10715.notesappcleanarchitecture.data.model.NoteDataModel;

import java.util.List;

public interface NoteApi {
    void saveNote(NoteDataModel noteDataModel);

    void deteleNote(NoteDataModel noteDataModel);

    List<NoteDataModel> getNotes();
}
