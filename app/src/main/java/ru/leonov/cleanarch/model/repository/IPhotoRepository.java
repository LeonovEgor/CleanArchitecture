package ru.leonov.cleanarch.model.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.leonov.cleanarch.model.entities.PhotoContainer;

public interface IPhotoRepository {
    Observable<List<PhotoContainer>> getPhotos(String search);
}
