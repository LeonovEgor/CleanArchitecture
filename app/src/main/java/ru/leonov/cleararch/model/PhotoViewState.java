package ru.leonov.cleararch.model;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.Nullable;
import ru.leonov.cleararch.model.entities.PhotoContainer;

public class PhotoViewState {
    private final boolean isLoading;
    @Nullable
    private final Throwable error;
    @Nullable
    private final List<PhotoContainer> photoContainerList;

    public PhotoViewState(boolean isLoading, Throwable error, List<PhotoContainer> photoContainerList) {
        this.isLoading = isLoading;
        this.error = error;
        this.photoContainerList = photoContainerList;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public Throwable getError() {
        return error;
    }

    public List<PhotoContainer> getPhotos() {
        return photoContainerList;
    }
}
