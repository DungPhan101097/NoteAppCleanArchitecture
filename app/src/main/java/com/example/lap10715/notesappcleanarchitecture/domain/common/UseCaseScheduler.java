package com.example.lap10715.notesappcleanarchitecture.domain.common;

import com.example.lap10715.notesappcleanarchitecture.domain.usecase.BaseUseCase;

public interface UseCaseScheduler {

    void execute(Runnable runnable);

    <V extends BaseUseCase.ResponseValues> void onSuccess(final V response,
                                                     final BaseUseCase.UseCaseCallback<V> useCaseCallback);
    <V extends BaseUseCase.ResponseValues> void onError(
                                                       final BaseUseCase.UseCaseCallback<V> useCaseCallback);
}
