package ru.leonov.cleanarch.model.interactor.photos;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.leonov.cleanarch.model.data.PhotoPositionalDataSource;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.model.repository.IPhotoRepository;

public class PhotoInteractor2 implements IPhotoInteractor {

    private final PhotoPositionalDataSource dataSource;
    private final IDataCallback callback;

    public PhotoInteractor2(PhotoPositionalDataSource dataSource, IDataCallback) {
        this.dataSource = dataSource;
    }

    @Override
    public void getPhotos(String search) {
        return dataSource.loadInitial(callback);
    }
}