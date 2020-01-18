package ru.leonov.cleanarch.viewmodel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.model.interactor.photos.IPhotoInteractor;

public class PhotoViewModel extends ViewModel {
    private static final String SAVE_SEARCH_STRING = "search_string";
    private static final String LOADING = "Loading...";

    private IPhotoInteractor interactor;
    private Disposable disposable;

    private MutableLiveData<List<PhotoContainer>> photoLiveData;
    private MutableLiveData<String> errorLiveData;
    private MutableLiveData<String> resultLiveData;

    private String searchString = "";

    private final Scheduler subscribeOn;
    private final Scheduler observeOn;


    public PhotoViewModel(Scheduler subscribeOn, Scheduler observeOn, IPhotoInteractor interactor) {
        this.subscribeOn = subscribeOn;
        this.observeOn = observeOn;
        this.interactor = interactor;

        this.photoLiveData = new MutableLiveData<>();
        this.errorLiveData = new MutableLiveData<>();
        this.resultLiveData = new MutableLiveData<>();
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            searchString = savedInstanceState.getString(SAVE_SEARCH_STRING, "");
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putString(SAVE_SEARCH_STRING, searchString);
    }

    public void onStart() {
        if (photoLiveData.getValue() == null) {
            interactor.getPhotos(searchString)
                    .subscribeOn(subscribeOn)
                    .observeOn(observeOn)
                    .subscribe(new PhotoObserver());
            resultLiveData.setValue(LOADING);
        }

    }

    @Override
    protected void onCleared() {
        if (disposable != null) {
            disposable.dispose();
        }

        super.onCleared();
    }

    public void onSearchPhotoAction(String str) {
        resultLiveData.setValue(LOADING);
        interactor.getPhotos(str)
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .subscribe(new PhotoObserver());
    }

    public LiveData<List<PhotoContainer>> getPhotos() {
        return photoLiveData;
    }

    public LiveData<String> getError() {
        return errorLiveData;
    }

    public LiveData<String> getResult() {
        return resultLiveData;
    }

    public String getSearchString() {
        return searchString;
    }

    private class PhotoObserver implements Observer<List<PhotoContainer>> {

        @Override
        public void onSubscribe(Disposable d) {
            disposable = d;
        }

        @Override
        public void onNext(List<PhotoContainer> photoContainerList) {
            photoLiveData.setValue(photoContainerList);
            resultLiveData.setValue("");
        }

        @Override
        public void onError(Throwable e) {
            errorLiveData.setValue(e.getMessage());
        }

        @Override
        public void onComplete() { }
    }
}