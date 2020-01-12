package ru.leonov.cleararch.model.interactor.photos;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import ru.leonov.cleararch.model.PhotoViewState;
import ru.leonov.cleararch.model.entities.PhotoContainer;
import ru.leonov.cleararch.model.repository.IPhotoRepository;

public class PhotoInteractor implements IPhotoInteractor {

    private final IPhotoRepository photosRepository;

    // интерактор использует репозиторий для получения данных
    // репозиторий - абстракция над слоем доступа к данным
    public PhotoInteractor(IPhotoRepository photosRepository) {
        this.photosRepository = photosRepository;
    }

    /**
     * Метод для получения данных
     * Возвращает ViewState c состоянием прогресс
     * и затем данные либо ошибку
     * Используется RxJava -- метод возвращает не просто одно значение,
     * но потенциально не ограниченное количество состояний
     */
    @Override
    public Observable<PhotoViewState> getPhotos() {
        return photosRepository.getPhotos()
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