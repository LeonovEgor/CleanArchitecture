package ru.leonov.cleararch.model.data;

import java.util.List;

import io.reactivex.Observable;
import ru.leonov.cleararch.model.entities.PhotoContainer;
import ru.leonov.cleararch.model.repository.IPhotoRepository;

public class PhotoRepository implements IPhotoRepository {
    private IPhotoDataSource photoDataSource;

    public PhotoRepository(IPhotoDataSource photoDataSource) {
        this.photoDataSource = photoDataSource;
    }

    @Override
    public Observable<List<PhotoContainer>> getPhotos(String search) {
        return photoDataSource.getPhotos(search);
    }
}
