package ru.leonov.cleanarch.viewmodel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.disposables.Disposable;
import ru.leonov.cleanarch.model.data.PhotoPositionalDataSource;
import ru.leonov.cleanarch.model.entities.PhotoContainer;

public class PhotoViewModel extends ViewModel {
    private static final String SAVE_SEARCH_STRING = "search_string";

    private MutableLiveData<List<PhotoContainer>> photoLiveData;
    private MutableLiveData<String> errorLiveData;
    private MutableLiveData<String> resultLiveData;

    private String searchString = "";

    private final PhotoPositionalDataSource dataSource;


    public PhotoViewModel(PhotoPositionalDataSource dataSource) {
        this.dataSource = dataSource;

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
    }

    public void onSearchPhotoAction(String str) {
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

    public PhotoPositionalDataSource getDataSource() {
        return dataSource;
    }
}