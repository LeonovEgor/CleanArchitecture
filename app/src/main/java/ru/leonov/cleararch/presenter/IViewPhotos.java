package ru.leonov.cleararch.presenter;

import io.reactivex.Observable;
import ru.leonov.cleararch.model.PhotoViewState;

public interface IViewPhotos {
    void render(PhotoViewState viewState);

    Observable<String> userActionIntent();
}
