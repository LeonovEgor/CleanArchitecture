package ru.leonov.cleanarch.model.repository;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import ru.leonov.cleanarch.model.entities.PhotoContainer;

public interface IPhotoRepository {
    List<PhotoContainer> getPhotos(String searchText, int perPage, int page) throws IOException;
}
