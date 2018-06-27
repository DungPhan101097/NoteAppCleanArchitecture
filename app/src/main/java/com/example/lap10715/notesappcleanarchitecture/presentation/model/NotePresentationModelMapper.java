package com.example.lap10715.notesappcleanarchitecture.presentation.model;

import com.example.lap10715.notesappcleanarchitecture.domain.model.NoteDomainModel;

import java.util.Calendar;

public class NotePresentationModelMapper {
    public static NoteDomainModel toNoteDomainModel(NotePresentationModel note){
       return new NoteDomainModel(note.getTitle(), note.getContent(), note.getCreateAt().getTime());
    }

    public static NotePresentationModel toNotePresentationModel(NoteDomainModel note){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(note.getCreateAt());

        return new NotePresentationModel(note.getTitle(), note.getContent(), calendar.getTime());
    }
}
