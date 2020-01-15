package ru.leonov.cleanarch.model.data;

import java.util.List;

import io.reactivex.Observable;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.model.repository.IPhotoRepository;

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
