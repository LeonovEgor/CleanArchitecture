package ru.leonov.cleanarch.model.interactor.photos;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import ru.leonov.cleanarch.model.PhotoViewState;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.model.repository.IPhotoRepository;

public class PhotoInteractor implements IPhotoInteractor {

    private final IPhotoRepository photosRepository;

    public PhotoInteractor(IPhotoRepository photosRepository) {
        this.photosRepository = photosRepository;
    }

    @Override
    public Observable<PhotoViewState> getPhotos(String search) {
        return photosRepository.getPhotos(search)
                // при получении данных возвращаем ViewState с данными
                .map(new Function<List<PhotoContainer>, PhotoViewState>() {
                    @Override
                    public PhotoViewState apply(List<PhotoContainer> photoContainerList) throws Exception {
                        return new PhotoViewState(false, null, photoContainerList);
                    }
                })
                // указываем сначала вернуть ViewState с состоянием загрузки
                .startWith(new PhotoViewState(true, null, null))
                // указываем в случае ошибки вернуть состояние с ошибкой
                .onErrorReturn(new Function<Throwable, PhotoViewState>() {
                    @Override
                    public PhotoViewState apply(Throwable throwable) throws Exception {
                        return new PhotoViewState(false, throwable, null);
                    }
                });
    }
}