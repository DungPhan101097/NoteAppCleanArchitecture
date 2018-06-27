package com.example.lap10715.notesappcleanarchitecture.data.repository.preference;

import android.content.Context;

import com.example.lap10715.notesappcleanarchitecture.R;
import com.example.lap10715.notesappcleanarchitecture.data.model.NoteDataModel;
import com.example.lap10715.notesappcleanarchitecture.data.repository.api.NoteApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NoteApiImpl implements NoteApi {

    private Context mContext;
    private Gson mGson;
    private Type mNoteListType;

    public NoteApiImpl(Context mContext) {
        this.mContext = mContext;
        this.mGson = new Gson();
        this.mNoteListType = new TypeToken<ArrayList<NoteDataModel>>() {
        }.getType();
    }

    @Override
    public void saveNote(NoteDataModel noteDataModel) {
        List<NoteDataModel> noteDataModels = getNotes();
        noteDataModels.add(noteDataModel);
        HandlerPreferenceStore.saveStringPreference(mContext, R.string.prefs_note_list,
                mGson.toJson(noteDataModels, mNoteListType));
    }

    @Override
    public void deteleNote(NoteDataModel noteDataModel) {
        List<NoteDataModel> noteDataModels = getNotes();
        noteDataModels.remove(noteDataModel);
        HandlerPreferenceStore.saveStringPreference(mContext, R.string.prefs_note_list,
                mGson.toJson(noteDataModels, mNoteListType));
    }

    @Override
    public List<NoteDataModel> getNotes() {
        String stringPreference = HandlerPreferenceStore.getStringPreference(mContext,
                R.string.prefs_note_list, "");
        List<NoteDataModel> noteDataModels = mGson.fromJson(stringPreference, mNoteListType);

        if(noteDataModels == null){
            noteDataModels = new ArrayList<>();
        }
        return noteDataModels;
    }
}
