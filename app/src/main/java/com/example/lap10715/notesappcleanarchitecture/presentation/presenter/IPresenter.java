package com.example.lap10715.notesappcleanarchitecture.presentation.presenter;

import com.example.lap10715.notesappcleanarchitecture.presentation.view.IView;

public interface IPresenter<T extends IView> {

    void attach(T view);

    void detach();

    T getView();


}
