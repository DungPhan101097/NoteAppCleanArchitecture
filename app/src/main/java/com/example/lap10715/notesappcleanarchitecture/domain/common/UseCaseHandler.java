package com.example.lap10715.notesappcleanarchitecture.domain.common;

import android.content.Intent;

import com.example.lap10715.notesappcleanarchitecture.domain.usecase.BaseUseCase;

public class UseCaseHandler {
    private static UseCaseHandler INSTANCE;
    private final UseCaseScheduler mUseCaseScheduler;

    private UseCaseHandler(UseCaseScheduler mUseCaseScheduler) {
        this.mUseCaseScheduler = mUseCaseScheduler;
    }

    public static UseCaseHandler getInstance() {
        if(INSTANCE == null){
            INSTANCE = new UseCaseHandler(new UseCaseThreadPoolExecutor());
        }
        return INSTANCE;
    }

    private <V extends BaseUseCase.ResponseValues> void notifySuccess(V response,
                       BaseUseCase.UseCaseCallback<V> useCaseCallback){
        mUseCaseScheduler.onSuccess(response, useCaseCallback);
    }

    private <V extends BaseUseCase.ResponseValues> void notifyError(
            BaseUseCase.UseCaseCallback<V> useCaseCallback){
        mUseCaseScheduler.onError(useCaseCallback);
    }

    public <T extends BaseUseCase.RequestValues, R extends BaseUseCase.ResponseValues> void execute(
            final BaseUseCase<T, R> useCase,
            T values,
            BaseUseCase.UseCaseCallback<R> useCaseCallback){

        useCase.setRequestValue(values);
        useCase.setUseCaseCallback(new UiCallbackWrapper<>(useCaseCallback, this));

        mUseCaseScheduler.execute(() -> useCase.run());

    }

    public static final class UiCallbackWrapper<V extends BaseUseCase.ResponseValues> implements
            BaseUseCase.UseCaseCallback<V>{

        private final BaseUseCase.UseCaseCallback<V> mCallback;
        private final UseCaseHandler mUseCasehandler;

        public UiCallbackWrapper(BaseUseCase.UseCaseCallback<V> mCallback, UseCaseHandler mUseCasehandler) {
            this.mCallback = mCallback;
            this.mUseCasehandler = mUseCasehandler;
        }

        @Override
        public void onSuccess(V response) {
            mUseCasehandler.notifySuccess(response, mCallback);
        }

        @Override
        public void onError() {
            mUseCasehandler.notifyError(mCallback);
        }
    }
}
