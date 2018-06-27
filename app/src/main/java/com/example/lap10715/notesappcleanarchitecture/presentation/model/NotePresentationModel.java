package com.example.lap10715.notesappcleanarchitecture.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class NotePresentationModel implements Parcelable {
    private String mTitle;
    private String mContent;
    private Date mCreateAt;

    public NotePresentationModel(String mTitle, String mContent, Date mCreateAt) {
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mCreateAt = mCreateAt;
    }

    protected NotePresentationModel(Parcel in){
        mTitle = in.readString();
        mContent = in.readString();
        mCreateAt = (Date) in.readSerializable();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mContent);
        dest.writeSerializable(mCreateAt);
    }

    public static final Creator<NotePresentationModel> CREATOR = new Creator<NotePresentationModel>() {
        @Override
        public NotePresentationModel createFromParcel(Parcel source) {
            return new NotePresentationModel(source);
        }

        @Override
        public NotePresentationModel[] newArray(int size) {
            return new NotePresentationModel[size];
        }
    };

    public String getTitle() {
        return mTitle;
    }

    public String getContent() {
        return mContent;
    }

    public Date getCreateAt() {
        return mCreateAt;
    }
}
