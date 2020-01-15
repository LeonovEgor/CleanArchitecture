package ru.leonov.cleanarch.presenter;

import io.reactivex.Observable;
import ru.leonov.cleanarch.model.PhotoViewState;

public interface IViewPhotos {
    void render(PhotoViewState viewState);

    Observable<String> userActionIntent();
    Observable<String> onStartIntent();
}
