package com.example.lap10715.notesappcleanarchitecture.presentation.presenter;

import com.example.lap10715.notesappcleanarchitecture.domain.common.UseCaseHandler;
import com.example.lap10715.notesappcleanarchitecture.domain.model.NoteDomainModelMapper;
import com.example.lap10715.notesappcleanarchitecture.domain.usecase.BaseUseCase;
import com.example.lap10715.notesappcleanarchitecture.domain.usecase.SaveNotesUseCase;
import com.example.lap10715.notesappcleanarchitecture.domain.usecase.ValidateFieldsUseCase;
import com.example.lap10715.notesappcleanarchitecture.presentation.contract.AddNoteContract;
import com.example.lap10715.notesappcleanarchitecture.presentation.model.NotePresentationModel;
import com.example.lap10715.notesappcleanarchitecture.presentation.model.NotePresentationModelMapper;

import java.util.Calendar;

public class AddNotePresenter extends BasePresenter<AddNoteContract.AddNoteContractView>
        implements AddNoteContract.AddNoteContractPresenter {

    private ValidateFieldsUseCase mValidateFieldsUseCase;
    private SaveNotesUseCase mSaveNotesUseCase;


    public AddNotePresenter(UseCaseHandler mUseCaseHandler, ValidateFieldsUseCase mValidateFieldsUseCase,
                            SaveNotesUseCase mSaveNotesUseCase) {
        super(mUseCaseHandler);
        this.mValidateFieldsUseCase = mValidateFieldsUseCase;
        this.mSaveNotesUseCase = mSaveNotesUseCase;
    }


    @Override
    public void validateField(String title, String content) {
        mUseCaseHandler.execute(mValidateFieldsUseCase,
                new ValidateFieldsUseCase.RequestValues(title, content),
                new BaseUseCase.UseCaseCallback<ValidateFieldsUseCase.ResponseValues>() {
                    @Override
                    public void onSuccess(ValidateFieldsUseCase.ResponseValues response) {
                        NotePresentationModel noteModel = new NotePresentationModel(title, content,
                                Calendar.getInstance().getTime());
                        saveNote(noteModel);

                        getView().closeSectionWithPositionResult();
                    }

                    @Override
                    public void onError() {
                        getView().showError("Please complete all the fields!");
                    }
                });
    }

    @Override
    public void saveNote(NotePresentationModel noteModel) {
        mUseCaseHandler.execute(mSaveNotesUseCase,
                new SaveNotesUseCase.RequestValues(NotePresentationModelMapper.toNoteDomainModel(noteModel)),
                new BaseUseCase.UseCaseCallback<SaveNotesUseCase.ResponseValues>() {
                    @Override
                    public void onSuccess(SaveNotesUseCase.ResponseValues response) {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}

