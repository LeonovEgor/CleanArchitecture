package ru.leonov.cleanarch.model.interactor.photos;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.model.repository.IPhotoRepository;

public class PhotoInteractor implements IPhotoInteractor {

    private final IPhotoRepository photosRepository;

    public PhotoInteractor(IPhotoRepository photosRepository) {
        this.photosRepository = photosRepository;
    }

    @Override
    public Observable<List<PhotoContainer>> getPhotos(String search) {
        return photosRepository.getPhotos(search)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}