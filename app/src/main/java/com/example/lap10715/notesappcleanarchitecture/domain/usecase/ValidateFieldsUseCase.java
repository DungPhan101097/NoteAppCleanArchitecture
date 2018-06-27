package com.example.lap10715.notesappcleanarchitecture.domain.usecase;

import android.text.TextUtils;

public class ValidateFieldsUseCase extends BaseUseCase<ValidateFieldsUseCase.RequestValues, ValidateFieldsUseCase.ResponseValues>{

    public ValidateFieldsUseCase() {
    }

    @Override
    protected void executeUseCase(RequestValues requestValue) {
        String title = requestValue.getTitle();
        String content = requestValue.getContent();

        if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)){
            getUseCaseCallback().onSuccess(new ResponseValues());
        }
        else{
            getUseCaseCallback().onError();
        }
    }


    public static class RequestValues implements BaseUseCase.RequestValues{
        private String mTitle;
        private String mContent;

        public RequestValues(String mTitle, String mContent) {
            this.mTitle = mTitle;
            this.mContent = mContent;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getContent() {
            return mContent;
        }
    }

    public static class ResponseValues implements  BaseUseCase.ResponseValues{

    }
}
