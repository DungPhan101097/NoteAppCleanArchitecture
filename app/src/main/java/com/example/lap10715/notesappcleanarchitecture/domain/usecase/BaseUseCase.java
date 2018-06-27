package com.example.lap10715.notesappcleanarchitecture.domain.usecase;

public abstract class BaseUseCase<Q extends BaseUseCase.RequestValues,
        P extends BaseUseCase.ResponseValues> {

    private Q mRequestValue;
    private UseCaseCallback<P> mUseCaseCallback;

    protected abstract void executeUseCase(Q requestValue);

    public void run(){
        executeUseCase(mRequestValue);
    }

    public Q getRequestValue() {
        return mRequestValue;
    }

    public void setRequestValue(Q mRequestValue) {
        this.mRequestValue = mRequestValue;
    }

    public UseCaseCallback<P> getUseCaseCallback() {
        return mUseCaseCallback;
    }

    public void setUseCaseCallback(UseCaseCallback<P> mUseCaseCallback) {
        this.mUseCaseCallback = mUseCaseCallback;
    }


    /**
     * Data passed a request.
     */
    public interface RequestValues {

    }

    /**
     * Data receive from a request.
     */
    public interface ResponseValues {

    }

    public interface UseCaseCallback<R>{
        void onSuccess(R response);
        void onError();
    }

}
