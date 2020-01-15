package ru.leonov.cleanarch.viewmodel;

import androidx.databinding.ObservableField;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.model.interactor.photos.IPhotoInteractor;

public class PhotoViewModel implements IPhotoViewModel {

    private IPhotoInteractor interactor;
    private Disposable disposable;

    private ObservableField<List<PhotoContainer>> photoObservableField;
    private ObservableField<String> errorObservableField;
    private ObservableField<String> resultObservableField;

    public PhotoViewModel(IPhotoInteractor interactor) {
        this.interactor = interactor;
        this.photoObservableField = new ObservableField<>();
        this.errorObservableField = new ObservableField<>();
        this.resultObservableField = new ObservableField<>();
    }

    @Override
    public void onStart() {
        interactor.getPhotos("").subscribe(new PhotoObserver());
    }

    @Override
    public void onStop() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public void onSearchPhotoAction() {
        resultObservableField.set("");
    }

    @Override
    public ObservableField<List<PhotoContainer>> getPhotos() {
        return photoObservableField;
    }

    @Override
    public ObservableField<String> getError() {
        return errorObservableField;
    }

    @Override
    public ObservableField<String> getResult() {
        return resultObservableField;
    }

    private class PhotoObserver implements Observer<List<PhotoContainer>> {

        @Override
        public void onSubscribe(Disposable d) {
            disposable = d;
        }

        @Override
        public void onNext(List<PhotoContainer> photoContainerList) {
            // полученные данные передаем в обозреваемое поле, которое уведомит подписчиков
            photoObservableField.set(photoContainerList);
        }

        @Override
        public void onError(Throwable e) {
            // ошибку тоже передаем в обозреваемое поле
            errorObservableField.set(e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    }
}
