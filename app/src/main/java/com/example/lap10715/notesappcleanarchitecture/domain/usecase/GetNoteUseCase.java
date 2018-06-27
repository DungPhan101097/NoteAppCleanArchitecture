package com.example.lap10715.notesappcleanarchitecture.domain.usecase;

import com.example.lap10715.notesappcleanarchitecture.data.model.NoteDataModel;
import com.example.lap10715.notesappcleanarchitecture.data.repository.api.NoteApi;
import com.example.lap10715.notesappcleanarchitecture.domain.model.NoteDomainModel;
import com.example.lap10715.notesappcleanarchitecture.domain.model.NoteDomainModelMapper;

import java.util.ArrayList;
import java.util.List;

public class GetNoteUseCase extends BaseUseCase<GetNoteUseCase.RequestValues, GetNoteUseCase.ResponseValues> {

    private NoteApi mNoteApi;

    public GetNoteUseCase(NoteApi mNoteApi) {
        this.mNoteApi = mNoteApi;
    }

    @Override
    protected void executeUseCase(RequestValues requestValue) {
        List<NoteDataModel> noteDataModelList = mNoteApi.getNotes();
        List<NoteDomainModel> noteDomainModelList = new ArrayList<>();

        for(NoteDataModel noteDataModel: noteDataModelList){
            noteDomainModelList.add(NoteDomainModelMapper.toNoteDomainModel(noteDataModel));
        }

        getUseCaseCallback().onSuccess(new ResponseValues(noteDomainModelList));
    }

    public static class RequestValues implements BaseUseCase.RequestValues{

    }

    public static class ResponseValues implements BaseUseCase.ResponseValues{
        private List<NoteDomainModel> noteDomainModels;

        public ResponseValues(List<NoteDomainModel> noteDomainModels) {
            this.noteDomainModels = noteDomainModels;
        }

        public List<NoteDomainModel> getNoteDomainModels() {
            return noteDomainModels;
        }
    }
}
