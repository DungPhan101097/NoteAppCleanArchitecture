package com.example.lap10715.notesappcleanarchitecture.domain.model;

import com.example.lap10715.notesappcleanarchitecture.data.model.NoteDataModel;

public class NoteDomainModel {
    private String mTitle;
    private String mContent;
    private Long mCreateAt;

    public NoteDomainModel(String mTitle, String mContent, Long mCreateAt) {
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mCreateAt = mCreateAt;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getContent() {
        return mContent;
    }

    public Long getCreateAt() {
        return mCreateAt;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || obj.getClass() != this.getClass())
            return false;
        NoteDataModel that = (NoteDataModel)obj;
        return this.getTitle().equals(that.getTitle()) && this.getContent().equals(that.getContent())
                && this.getCreateAt().equals(that.getCreateAt());
    }

    @Override
    public int hashCode() {
        int result = getContent().hashCode();
        result = 31 * result + getCreateAt().hashCode();
        return result;
    }
}
