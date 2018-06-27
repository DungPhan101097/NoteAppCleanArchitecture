package com.example.lap10715.notesappcleanarchitecture.domain.common;

import android.os.Handler;

import com.example.lap10715.notesappcleanarchitecture.domain.usecase.BaseUseCase;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class UseCaseThreadPoolExecutor implements UseCaseScheduler {

    private static final int CORE_THREAD = 2;
    private static final int MAX_THREAD = 4;
    private static final int TIME_OUT = 30;

    private Handler mUiHandler;
    private ThreadPoolExecutor threadPoolExecutor;

    public UseCaseThreadPoolExecutor() {
        this(new Handler(), new ThreadPoolExecutor(CORE_THREAD, MAX_THREAD, TIME_OUT, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(CORE_THREAD)));
    }

    private UseCaseThreadPoolExecutor(Handler mUiHandler, ThreadPoolExecutor threadPoolExecutor) {
        this.mUiHandler = mUiHandler;
        this.threadPoolExecutor = threadPoolExecutor;
    }

    @Override
    public void execute(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    @Override
    public <V extends BaseUseCase.ResponseValues> void onSuccess(V response,
                   BaseUseCase.UseCaseCallback<V> useCaseCallback) {
        mUiHandler.post(() -> useCaseCallback.onSuccess(response));

    }

    @Override
    public <V extends BaseUseCase.ResponseValues> void onError(
                   BaseUseCase.UseCaseCallback<V> useCaseCallback) {
        mUiHandler.post(() -> useCaseCallback.onError());
    }
}
