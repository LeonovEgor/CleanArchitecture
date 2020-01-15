package ru.leonov.cleararch.model.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.leonov.cleararch.model.entities.PhotoContainer;

public interface IPhotoRepository {
    Observable<List<PhotoContainer>> getPhotos(String search);
}
