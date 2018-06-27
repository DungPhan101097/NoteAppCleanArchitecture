package com.example.lap10715.notesappcleanarchitecture.presentation.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.lap10715.notesappcleanarchitecture.data.repository.preference.NoteApiImpl;
import com.example.lap10715.notesappcleanarchitecture.domain.common.UseCaseHandler;
import com.example.lap10715.notesappcleanarchitecture.presentation.presenter.IPresenter;

public abstract class BaseActivity extends AppCompatActivity implements IView{
    protected UseCaseHandler mUseCaseHandler;
    protected NoteApiImpl mPreferenceNoteApi;

    @Nullable
    private IPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependences();
    }

    private void injectDependences() {
        mUseCaseHandler = UseCaseHandler.getInstance();
        mPreferenceNoteApi = new NoteApiImpl(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mPresenter != null){
            mPresenter.attach(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mPresenter != null){
            mPresenter.detach();
        }
    }

    protected void bindPresenterToView(IPresenter presenter){
        this.mPresenter = presenter;
    }
}
