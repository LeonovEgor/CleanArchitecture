package ru.leonov.cleararch.model.data;

import java.util.List;

import io.reactivex.Observable;
import ru.leonov.cleararch.model.entities.PhotoContainer;

public interface IPhotoDadaSource {
    Observable<List<PhotoContainer>> getPhotos(String search);
}
