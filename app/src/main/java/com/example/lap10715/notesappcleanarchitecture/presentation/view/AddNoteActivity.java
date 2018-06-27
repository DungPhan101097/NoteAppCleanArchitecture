package com.example.lap10715.notesappcleanarchitecture.presentation.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lap10715.notesappcleanarchitecture.R;
import com.example.lap10715.notesappcleanarchitecture.domain.usecase.SaveNotesUseCase;
import com.example.lap10715.notesappcleanarchitecture.domain.usecase.ValidateFieldsUseCase;
import com.example.lap10715.notesappcleanarchitecture.presentation.contract.AddNoteContract;
import com.example.lap10715.notesappcleanarchitecture.presentation.presenter.AddNotePresenter;

public class AddNoteActivity extends BaseActivity implements AddNoteContract.AddNoteContractView {
    private EditText mEdtTitle;
    private EditText mEdtContent;
    private Button  mBtnAddNote;
    private AddNotePresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        createPresenter();
        bindPresenterToView(mPresenter);

        mEdtContent = findViewById(R.id.edt_content);
        mEdtTitle = findViewById(R.id.edt_title);
        mBtnAddNote =  findViewById(R.id.btn_add);

        mBtnAddNote.setOnClickListener(v -> {
            mPresenter.validateField(mEdtTitle.getText().toString(), mEdtContent.getText().toString());
        });

        if(getSupportActionBar()!= null){
            getSupportActionBar().setTitle("Add new activity");
        }
    }

    private void createPresenter() {
        ValidateFieldsUseCase validateFieldsUseCase = new ValidateFieldsUseCase();
        SaveNotesUseCase saveNotesUseCase = new SaveNotesUseCase(super.mPreferenceNoteApi);
        mPresenter = new AddNotePresenter(mUseCaseHandler, validateFieldsUseCase, saveNotesUseCase);
    }


    @Override
    public void closeSectionWithPositionResult() {
        finish();
    }

    @Override
    public void showError(String e) {
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
    }
}
