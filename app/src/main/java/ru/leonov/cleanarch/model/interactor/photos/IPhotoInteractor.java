package ru.leonov.cleanarch.model.interactor.photos;

import java.util.List;

import io.reactivex.Observable;
import ru.leonov.cleanarch.model.entities.PhotoContainer;

public interface IPhotoInteractor {
    Observable<List<PhotoContainer>> getPhotos(String search);
}
