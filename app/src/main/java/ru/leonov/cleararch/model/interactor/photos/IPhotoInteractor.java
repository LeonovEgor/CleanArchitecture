package ru.leonov.cleararch.model.interactor.photos;

import io.reactivex.Observable;
import ru.leonov.cleararch.model.PhotoViewState;

public interface IPhotoInteractor {
    Observable<PhotoViewState> getPhotos(String search);
}
