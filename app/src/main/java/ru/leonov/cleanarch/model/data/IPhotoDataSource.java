package ru.leonov.cleanarch.model.data;

import java.util.List;

import io.reactivex.Observable;
import ru.leonov.cleanarch.model.entities.PhotoContainer;

public interface IPhotoDataSource {
    Observable<List<PhotoContainer>> getPhotos(String search);
}
