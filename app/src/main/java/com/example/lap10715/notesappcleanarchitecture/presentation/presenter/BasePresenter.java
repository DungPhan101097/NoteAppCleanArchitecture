package com.example.lap10715.notesappcleanarchitecture.presentation.presenter;

import com.example.lap10715.notesappcleanarchitecture.domain.common.UseCaseHandler;
import com.example.lap10715.notesappcleanarchitecture.presentation.view.IView;

public class BasePresenter<T extends IView> implements IPresenter<T> {

    protected UseCaseHandler mUseCaseHandler;
    private T mView;

    public BasePresenter(UseCaseHandler mUseCaseHandler) {
        this.mUseCaseHandler = mUseCaseHandler;
    }


    @Override
    public void attach(T view) {
        this.mView = view;
    }

    @Override
    public void detach() {
        this.mView = null;
    }

    @Override
    public T getView() {
        return mView;
    }
}
