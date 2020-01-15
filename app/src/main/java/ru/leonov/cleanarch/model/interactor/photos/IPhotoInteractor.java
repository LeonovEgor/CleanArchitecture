package ru.leonov.cleanarch.model.interactor.photos;

import io.reactivex.Observable;
import ru.leonov.cleanarch.model.PhotoViewState;

public interface IPhotoInteractor {
    Observable<PhotoViewState> getPhotos(String search);
}
