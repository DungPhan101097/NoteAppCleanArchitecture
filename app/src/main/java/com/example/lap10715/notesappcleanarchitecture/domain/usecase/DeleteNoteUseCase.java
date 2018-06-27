package com.example.lap10715.notesappcleanarchitecture.domain.usecase;

import com.example.lap10715.notesappcleanarchitecture.data.model.NoteDataModel;
import com.example.lap10715.notesappcleanarchitecture.data.repository.api.NoteApi;
import com.example.lap10715.notesappcleanarchitecture.domain.model.NoteDomainModel;
import com.example.lap10715.notesappcleanarchitecture.domain.model.NoteDomainModelMapper;

public class DeleteNoteUseCase extends BaseUseCase<DeleteNoteUseCase.RequestValues, DeleteNoteUseCase.ReponseValues> {

    private NoteApi mNoteApi;

    public DeleteNoteUseCase(NoteApi mNoteApi) {
        this.mNoteApi = mNoteApi;
    }

    @Override
    protected void executeUseCase(RequestValues requestValue) {
        NoteDomainModel noteDomainModel = requestValue.getNoteDomainModel();
        NoteDataModel noteDataModel = NoteDomainModelMapper.toNoteDataModel(noteDomainModel);
        mNoteApi.deteleNote(noteDataModel);

        getUseCaseCallback().onSuccess(new ReponseValues());
    }

    public static class RequestValues implements BaseUseCase.RequestValues{
        private NoteDomainModel mNoteDomainModel;

        public RequestValues(NoteDomainModel mNoteDomainModel) {
            this.mNoteDomainModel = mNoteDomainModel;
        }

        public NoteDomainModel getNoteDomainModel() {
            return mNoteDomainModel;
        }
    }

    public static class ReponseValues implements  BaseUseCase.ResponseValues{

    }
}
