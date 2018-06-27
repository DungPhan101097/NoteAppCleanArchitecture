package com.example.lap10715.notesappcleanarchitecture.domain.model;

import com.example.lap10715.notesappcleanarchitecture.data.model.NoteDataModel;

public class NoteDomainModelMapper {

    public static NoteDataModel toNoteDataModel(NoteDomainModel noteDomainModel){
        return new NoteDataModel(noteDomainModel.getTitle(), noteDomainModel.getContent(),
                noteDomainModel.getCreateAt());
    }

    public static NoteDomainModel toNoteDomainModel(NoteDataModel noteDataModel){
        return new NoteDomainModel(noteDataModel.getTitle(), noteDataModel.getContent(),
                noteDataModel.getCreateAt());
    }
}
