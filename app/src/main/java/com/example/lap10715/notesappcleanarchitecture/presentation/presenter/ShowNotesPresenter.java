package com.example.lap10715.notesappcleanarchitecture.presentation.presenter;

import com.example.lap10715.notesappcleanarchitecture.domain.common.UseCaseHandler;
import com.example.lap10715.notesappcleanarchitecture.domain.model.NoteDomainModel;
import com.example.lap10715.notesappcleanarchitecture.domain.usecase.BaseUseCase;
import com.example.lap10715.notesappcleanarchitecture.domain.usecase.DeleteNoteUseCase;
import com.example.lap10715.notesappcleanarchitecture.domain.usecase.GetNoteUseCase;
import com.example.lap10715.notesappcleanarchitecture.presentation.contract.ShowNotesContract;
import com.example.lap10715.notesappcleanarchitecture.presentation.model.NotePresentationModel;
import com.example.lap10715.notesappcleanarchitecture.presentation.model.NotePresentationModelMapper;
import com.example.lap10715.notesappcleanarchitecture.presentation.view.IView;

public class ShowNotesPresenter extends BasePresenter<ShowNotesContract.View>
        implements ShowNotesContract.Presenter{

    private final GetNoteUseCase mGetNoteUseCase;
    private final DeleteNoteUseCase mDeleteNoteUseCase;

    public ShowNotesPresenter(UseCaseHandler mUseCaseHandler, GetNoteUseCase mGetNoteUseCase,
                              DeleteNoteUseCase mDeleteNoteUseCase) {
        super(mUseCaseHandler);
        this.mGetNoteUseCase = mGetNoteUseCase;
        this.mDeleteNoteUseCase = mDeleteNoteUseCase;
    }

    @Override
    public void populateNotesFromPrefs() {
        mUseCaseHandler.execute(mGetNoteUseCase,
                new GetNoteUseCase.RequestValues(),
                new BaseUseCase.UseCaseCallback<GetNoteUseCase.ResponseValues>() {
                    @Override
                    public void onSuccess(GetNoteUseCase.ResponseValues response) {
                        getView().clearNoteList();

                        for(NoteDomainModel noteModel : response.getNoteDomainModels()){
                            NotePresentationModel note = NotePresentationModelMapper.toNotePresentationModel(noteModel);

                            getView().showNewNote(note);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void showListOrEmptyPlaceholder(int oldItemCount, int newItemCount) {
        if(newItemCount > 0){
            getView().showList();
        }
        else{
            getView().showEmptyPlaceholder();
        }
    }

    @Override
    public void deleteNoteFromMemory(NotePresentationModel noteModel) {
        NoteDomainModel noteDomain = NotePresentationModelMapper.toNoteDomainModel(noteModel);

        mUseCaseHandler.execute(mDeleteNoteUseCase,
                new DeleteNoteUseCase.RequestValues(noteDomain),
                new BaseUseCase.UseCaseCallback<DeleteNoteUseCase.ReponseValues>() {
                    @Override
                    public void onSuccess(DeleteNoteUseCase.ReponseValues response) {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
