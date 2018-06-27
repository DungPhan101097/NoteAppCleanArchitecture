package com.example.lap10715.notesappcleanarchitecture.domain.usecase;

import com.example.lap10715.notesappcleanarchitecture.data.model.NoteDataModel;
import com.example.lap10715.notesappcleanarchitecture.data.repository.api.NoteApi;
import com.example.lap10715.notesappcleanarchitecture.domain.model.NoteDomainModel;
import com.example.lap10715.notesappcleanarchitecture.domain.model.NoteDomainModelMapper;

public class SaveNotesUseCase extends BaseUseCase<SaveNotesUseCase.RequestValues, SaveNotesUseCase.ResponseValues> {

    private NoteApi mNoteApi;

    public SaveNotesUseCase(NoteApi mNoteApi) {
        this.mNoteApi = mNoteApi;
    }

    @Override
    protected void executeUseCase(RequestValues requestValue) {
        NoteDomainModel noteDomainModel = requestValue.getNoteDomainModel();
        NoteDataModel noteDataModel = NoteDomainModelMapper.toNoteDataModel(noteDomainModel);

        mNoteApi.saveNote(noteDataModel);
        getUseCaseCallback().onSuccess(new ResponseValues());
    }

    public static class RequestValues implements BaseUseCase.RequestValues{
        private NoteDomainModel mNoteDomainModel;

        public RequestValues(NoteDomainModel noteDomainModel) {
            this.mNoteDomainModel = noteDomainModel;
        }

        public NoteDomainModel getNoteDomainModel() {
            return mNoteDomainModel;
        }
    }

    public static class ResponseValues implements BaseUseCase.ResponseValues{

    }
}
