package ru.leonov.cleararch.model.data;

import java.util.List;

import io.reactivex.Observable;
import ru.leonov.cleararch.model.entities.PhotoContainer;

public interface IPhotoDataSource {
    Observable<List<PhotoContainer>> getPhotos(String search);
}
